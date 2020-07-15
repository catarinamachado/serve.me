package EA_ServeMe.beans;

import EA_ServeMe.responses.*;
import EA_ServeMe.util.*;
import org.json.JSONObject;
import org.orm.PersistentException;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import servico.Proposta;
import servico.Servico;
import servico.ServicoDAO;
import servico.ServicoState;
import utilizador.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Prestador_Perfil {

    private static final String SECRETKEY =  "servemencriptkey";
    private static final String TAG =  "[PRESTADORPERFIL]";


    public static Prestador getPrestadorbyEmail(String email){
        String q = "Email = '" + email + "'";
        try {
            Prestador p = PrestadorDAO.listPrestadorByQuery(q, "Email")[0];
            return p;
        }catch (PersistentException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public static AuthResponse loginTokenPrestador(String email, String toke){
        String token =  toke;
        String q = "Email = '" + email + "'";
        Prestador[] prestadores;
        try {
            prestadores = PrestadorDAO.listPrestadorByQuery(q,"Email");
            Prestador p = (Prestador) prestadores[0];
            String nome = p.getNome();
            AuthResponse ar = new AuthResponse(token,nome);
            return  ar;
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public static List<String> registerPrestador(Prestador p){
        List<String> error = new ArrayList<>();
        error.add("Error");

        // Verificação de inserção na bd

        String query_e = "Email = '" + p.getEmail() + "'"; // Query que verifica o email
        Prestador[] bd_e = null;

        String query_n = "Nif = " + p.getNif(); // Query verificação NIF
        Prestador[] bd_n = null;

        String query_t = "NumTelemovel = " + p.getNumTelemovel(); // Query verificação do numTelemovel
        Prestador[] bd_t = null;
        try {
            bd_e = PrestadorDAO.listPrestadorByQuery(query_e,"Email");
            bd_n = PrestadorDAO.listPrestadorByQuery(query_n,"Nif");
            bd_t = PrestadorDAO.listPrestadorByQuery(query_t,"NumTelemovel");
        } catch (PersistentException e) {
            Log.e("[BD]","Impossível aceder à BD" );
        }


        if (bd_e == null || bd_n == null || bd_t == null ) {
            error.add("BD");
            return error;
        }

        if (bd_e.length != 0)
             error.add("Email já está em uso");
        if (bd_n.length != 0)
            error.add("Nif já está em uso");
        if (bd_t.length != 0)
            error.add("Telemovel já está em uso");

        if (error.size() > 1) return error;


        List<String> success = new ArrayList<>();
        success.add("OK");

        //Decode from frontend and Encode to DB

        String prestador_password = p.getPassword();
        prestador_password = Prestador_Perfil.decodePassword(prestador_password);  //PROD: ADD THIS -- Função que faz decode da password vinda do frontend
        String new_Password = new BCryptPasswordEncoder(11).encode( prestador_password);
        p.setPassword(new_Password);
        /* PROD : ADD THIS */
        try {
            PrestadorDAO.save(p);
            Log.i(TAG,"Prestador Saved Succesfully");
            return success;
        } catch (PersistentException e) {
            e.printStackTrace();
            Log.e(TAG,"Error Saving Prestador");
        }

        error.add("BD");
        return  error;
        // return success; // PROD: DELETE THIS

    }

    public static Prestador parseUtilizadorJSON(String json) throws Exception {
        JSONObject obj = new JSONObject(json);
        try {
            String nome = "";
            nome = obj.getString("nome");
            String email = "";
            email = obj.getString("email");
            String password = "";
            password = obj.getString("password");
            long numT = 0;
            String distrito = "";
            String concelho = "";
            String freguesia = "";
            String morada = "";
            long nif = 0;
            /* PROD : ADD THIS */
            numT = obj.getLong("telemovel");
            distrito = obj.getString("distrito");
            concelho = obj.getString("concelho");
            freguesia = obj.getString("freguesia");
            morada =  obj.getString("morada");
            nif =  obj.getLong("nif");

            return buildPrestador(nome,email,password,numT,distrito,concelho,freguesia,morada,nif);
        }
        catch (Exception e){
            Log.e(TAG,"Missing Fields on JSON");
            throw new Exception("Missing Fields");

        }
    }

    private static Prestador buildPrestador(String nome, String email, String password, long numT , String distrito, String concelho, String freguesia ,String morada, long nif){
        Prestador p = new Prestador();
        p.setNome(nome);p.setEmail(email);p.setPassword(password);p.setNumTelemovel(numT);p.setClassificacao(5.0);
        p.setDistrito(distrito);p.setConcelho(concelho);p.setFreguesia(freguesia);p.setMorada(morada);
        p.setNif(nif);p.setNumServicosCancelados(0);p.setNumServicosRealizados(0);
        return p;
    }

    public static String decodePassword(String password) {
        String encodedBase64Key = Crypt.encodeKey(SECRETKEY);
        return  Crypt.decrypt(password,encodedBase64Key);

    }

    public static String parseEmailPresJSON(String body) throws Exception {

        JSONObject obj = new JSONObject(body);
        try {
            String email = "";
            email = obj.getString("email_pres");
            return email;
        } catch (Exception e) {
            Log.e(TAG, "Missing Fields on JSON");
        }
        return "ERROR";

    }

    @Bean
    public static MyProfileResponse checkprofile(String email) {
        String q = "Email = '" + email + "'";
        Prestador[] prestadores;
        try {
            prestadores = PrestadorDAO.listPrestadorByQuery(q, "Email");
            if (prestadores.length > 0) {
                Prestador p = prestadores[0];
                MyProfileResponse pr = new MyProfileResponse(p.getNome(), p.getEmail(), p.getNumTelemovel(),p.getNif(), p.getMorada(), p.getFreguesia(), p.getConcelho(), p.getDistrito());
                Log.i(TAG,"Prestador profile sent");
                return pr;
            }
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static MyProfileResponse parseUpdateProfile(String body){
        JSONObject jsonObject = new JSONObject(body);
        String name = "";
        long nrTelm =0;
        String morada ="";
        String freg = "";
        String conc = "";
        String distrito = "";

        try{
            name = jsonObject.getString("nome");

            nrTelm = jsonObject.getLong("nrTelm");

            morada = jsonObject.getString("morada");

            freg = jsonObject.getString("freguesia");

            conc = jsonObject.getString("concelho");

            distrito = jsonObject.getString("distrito");

            if(name == "" || nrTelm == 0 || morada =="" || freg == "" || conc == "" || distrito == "")
                return null;

            //Email cant be update, so is ""
            MyProfileResponse pr = new MyProfileResponse(name,"",nrTelm,0,morada,freg,conc,distrito);
            return  pr;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    return null;
    }

    public static List<String> parsePasswordUpdate(String body){
        //Pos 0: Current Passowrd
        //Pos 1: New Passowrd
        JSONObject jsonObject = new JSONObject(body);
        //verificar que password atual é esta e atualizar para a nova
        List<String> res = new ArrayList<>();
        try{
            String pw_atual = "";
            pw_atual = jsonObject.getString("pw_atual");
            String pw_nova = "";
            pw_nova = jsonObject.getString("pw_nova");
            res.add(pw_atual);
            res.add(pw_nova);
            return res;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @Bean
    public static ResponseEntity updatePassoword(String email, String pw_atual, String pw_nova) {
        String q = "Email = '" + email + "'";
        Prestador[] prestadors;
        try {
            prestadors = PrestadorDAO.listPrestadorByQuery(q, "Email");
            if (prestadors.length > 0) {
                Prestador p = (Prestador) prestadors[0];
                String password_old = p.getPassword();
                boolean same = new BCryptPasswordEncoder(11).matches(pw_atual,password_old);
                //If current password not matches
                if (!same) {
                    Log.e(TAG, "Prestador current password doesn't match!");
                    ErrorResponse er = new ErrorResponse();
                    er.setLocalError("EditPassword");
                    er.addMsg("CurrentPassword");
                    return  ResponseEntity.badRequest().body(er);
                } else {
                    Log.i(TAG, "Prestador current password matches");
                    //If matches, lets update
                    int res = PrestadorDAO.updatePrestadorPW(email, pw_nova);
                    if (res == 1) {
                        Log.i(TAG, "Prestador password succssefully updated");
                        return ResponseEntity.ok().body("OK");
                    } else {
                        Log.e(TAG, "Prestador password update went wrong");
                        ErrorResponse er = new ErrorResponse();
                        er.setLocalError("EditPassword");
                        er.addMsg("DataBase");
                        return ResponseEntity.badRequest().body(er);
                    }
                }
            }
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public static ClienteProfResponse checkClienteProfile(String email_cli){
        String q = "Email = '" + email_cli + "'";
        try {
            Cliente[] cli = ClienteDAO.listClienteByQuery(q, "Email");
            if (cli.length <= 0) {
                ErrorResponse er = new ErrorResponse();
                er.setLocalError("Cliente not found");
                Log.e(TAG,"Cliente profile not found");
                return null;
            } else {
                Cliente c = cli[0];
                int id = c.getID();
                String query = "ClienteID = ' " + id + "'";
                // PROD: ADD THIS
                // Servico[] serv = ServicoDAO.listServicoByQuery(query,"ID");
                //if(serv.length > 0 ) {
                //Bottom lines HERE
                // }
                //else{
                //Log.e(TAG,"Cant acess this profile from external ways");
                //ErrorResponse er = new ErrorResponse();
                //er.setLocalError("Cant acess this profile from external ways);
                //return ResponseEntity.badrequest().body(er);
                // }
                Avaliacao_Cliente[] avaliacao_clientes = Avaliacao_ClienteDAO.listAvaliacao_ClienteByQuery(query, "ID");
                List<AvaliacaoResponse> avaliacoes = new ArrayList<>();
                for(Avaliacao_Cliente a : avaliacao_clientes){
                    AvaliacaoResponse ar = new AvaliacaoResponse(a.getPrestador().getNome(),a.getClassificacao(),a.getOpiniao());
                    avaliacoes.add(ar);
                }
                ClienteProfResponse cli_prof = new ClienteProfResponse(c.getNome(), c.getEmail(), c.getNumTelemovel(), c.getMorada(), c.getFreguesia(), c.getConcelho(), c.getDistrito(),c.getClassificacao(),c.getNumServicosRealizados(),c.getNumServicosCancelados(), avaliacoes);
                Log.i(TAG,"Cliente profile sent with success");
                return cli_prof;
            }
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<String> parseAvaliacaofromCli(String body){
        List<String> res = new ArrayList<>();
        String email_pres = "";
        String classificacao = "";
        String opiniao = "";
        String idServ = "";
        JSONObject jsonObject = new JSONObject(body);
        try{
            email_pres = jsonObject.getString("email_prestador");
            classificacao = String.valueOf(jsonObject.getDouble("classificacao"));
            opiniao = jsonObject.getString("opiniao");
            idServ = jsonObject.getString("idServico");

            if(email_pres.equals("") || classificacao.equals("") || opiniao.equals("") || idServ.equals(""))
                return null;
            else{
                //pos 0 -> email_cli
                res.add(email_pres);
                //pos 1 -> classificacao
                res.add(classificacao);
                //pos 2 -> opiniao
                res.add(opiniao);
                //pos 3 -> idServico
                res.add(idServ);
                return res;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public static int avaliar_prestador(String email_cli, List<String> request){
        String email_pres = request.get(0);
        double classificacao = Double.valueOf(request.get(1));
        String opiniao = request.get(2);
        int idServico = Integer.valueOf(request.get(3));

        try {
            Servico servico = ServicoDAO.getServicoByORMID(idServico);
            if(servico.getEstado() < ServicoState.CREATED.v())
                return -2;
            if(servico.getEstado() == ServicoState.PROVIDERDONE.v() || servico.getEstado() == ServicoState.EVALUATED.v())
                return -1;
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        String q = "Email = '" + email_cli + "'";
        String q_2 = "Email = '" + email_pres + "'";
        Cliente c = null;
        try {
            Cliente[] clis = ClienteDAO.listClienteByQuery(q,"Email");
            c = clis[0];
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        Prestador[] pres;
        Prestador p = null;
        try {
            pres = PrestadorDAO.listPrestadorByQuery(q_2,"Email");
            p = pres[0];
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        //Update Numero Servicos Realizados
        p.setNumServicosRealizados(p.getNumServicosRealizados() + 1);
        //Update Classificacao
        double nova_classificação = (p.getClassificacao() * p.getNumServicosRealizados() + classificacao)/(p.getNumServicosRealizados() + 1);
        p.setClassificacao(nova_classificação);
        try {
            PrestadorDAO.save(p);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        Avaliacao_Prestador aval = new Avaliacao_Prestador();
        aval.setClassificacao(classificacao);
        aval.setOpiniao(opiniao);
        aval.setCliente(c);
        try {
            p.avaliacoes.add(aval);
            PrestadorDAO.save(p);
            //Avaliacao_PrestadorDAO.save(aval);
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        try {
            Servico servico = ServicoDAO.getServicoByORMID(idServico);
            if(servico.getEstado() == ServicoState.CREATED.v()){
                servico.setEstado(ServicoState.PROVIDERDONE.v());
                ServicoDAO.save(servico);
            }
            if(servico.getEstado() == ServicoState.CLIENTDONE.v()){
                servico.setEstado(ServicoState.EVALUATED.v());
                ServicoDAO.save(servico);
            }
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public static MyStatsResponse generateStats(List<Servico> done_services) {
        MyStatsResponse msr = new MyStatsResponse();
        //Get the year of the stats
        int current_year = Calendar.getInstance().get(Calendar.YEAR);
        msr.setAno(current_year);

        List<Servico> este_ano = new ArrayList<>();

        //GetTotalServices in Current Year and Total Earned
        int total_servicos_anual = 0;
        double total_earned = 0;
        for(Servico sr: done_services){
            LocalDateTime hora = DateUtils.asLocalDateTime(sr.getProposta().getHoraInicio());
            if(hora.getYear() == current_year){
                este_ano.add(sr);
                total_servicos_anual++;
                if(sr.getProposta().getVencedora() > 0){
                    total_earned += sr.getPedido().getDuracao() * sr.getProposta().getPrecoProposto(); }

            }
        }
        msr.setServicos_anual(total_servicos_anual);
        msr.setGanho_anual(total_earned);

        //Initialize Variabels
        List<Dot> ganhos_por_mes = new ArrayList<>();
        List<Dot> servicos_por_mes = new ArrayList<>();
        List<Dot> servicos_por_subcat = new ArrayList<>();


        //Initialize Lists by Months
        String[] shortMonths = new DateFormatSymbols().getShortMonths();
        for (int i = 0; i < (shortMonths.length-1); i++) {
            String shortMonth = shortMonths[i];
            Dot d1 = new Dot(shortMonth,0);
            Dot d2 = new Dot(shortMonth,0);
            ganhos_por_mes.add(d1);
            servicos_por_mes.add(d2);
        }


        //Build lists
        for(Servico s: este_ano){
            int mes = DateUtils.asLocalDateTime(s.getPedido().getData()).getMonthValue();
            servicos_por_mes.get(mes-1).incY(1);
            ganhos_por_mes.get(mes-1).incY(s.getPedido().getDuracao() * s.getProposta().getPrecoProposto());


            if(!servicos_por_subcat.contains(s.getPedido().getCategoria().getNome()))
                servicos_por_subcat.add(new Dot(s.getPedido().getCategoria().getNome(),1));
            else{
                for(Dot d: servicos_por_subcat)
                    if(d.getEixo_x().equals(s.getPedido().getCategoria().getNome()))
                        d.incY(1);
            }
        }
        //Set Lists generated
        msr.setGanhos_por_mes(ganhos_por_mes);
        msr.setServicos_por_mes(servicos_por_mes);
        msr.setServicos_por_subcat(servicos_por_subcat);
        return msr;
    }
}
