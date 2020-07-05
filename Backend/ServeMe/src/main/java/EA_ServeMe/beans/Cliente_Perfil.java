package EA_ServeMe.beans;

import EA_ServeMe.util.*;
import org.json.JSONObject;
import org.orm.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import servico.Servico;
import servico.ServicoDAO;
import utilizador.*;


import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cliente_Perfil {

    private static final String SECRETKEY = "servemencriptkey";
    private static final String TAG = "[CLIENTEPERFIL]";

    @Autowired
    static JwtUtil jwtUtil;


    @Bean
    public static AuthResponse loginTokenCliente(String email, String toke) {
        String token = toke;
        String q = "Email = '" + email + "'";
        Cliente[] clientes;
        Cliente c = getClientebyEmail(email);
        String nome = c.getNome();
        AuthResponse ar = new AuthResponse(token,nome);
        return  ar;
    }

    private static Cliente buildCliente(String nome, String email, String password, long numT, String distrito, String concelho, String freguesia, String morada, long nif) {
        Cliente c = new Cliente();
        c.setNome(nome);
        c.setEmail(email);
        c.setPassword(password);
        c.setNumTelemovel(numT);
        c.setClassificacao(5.0);
        c.setDistrito(distrito);
        c.setConcelho(concelho);
        c.setFreguesia(freguesia);
        c.setMorada(morada);
        c.setNif(nif);
        c.setNumServicosCancelados(0);
        c.setNumServicosRealizados(0);
        return c;
    }

    @Bean
    public static List<String> registerCliente(Cliente c) { //(String nome, String email, String password, long numT , String morada, long nif){
        List<String> error = new ArrayList<>();
        error.add("Error");

        // Verificação de inserção na bd

        String query_e = "Email = '" + c.getEmail() + "'"; // Query que verifica o email
        Cliente[] bd_e = null;

        String query_n = "Nif = " + c.getNif(); // Query verificação NIF
        Cliente[] bd_n = null;

        String query_t = "NumTelemovel = " + c.getNumTelemovel(); // Query verificação do numTelemovel
        Cliente[] bd_t = null;
        try {
            bd_e = ClienteDAO.listClienteByQuery(query_e, "Email");
            bd_n = ClienteDAO.listClienteByQuery(query_n, "Nif");
            bd_t = ClienteDAO.listClienteByQuery(query_t, "NumTelemovel");
        } catch (PersistentException e) {
            Log.e("[DB]", "Impossível aceder à BD");
        }

        if (bd_e == null || bd_n == null || bd_t == null) {
            error.add("BD");
            return error;
        }
        if (bd_e.length != 0)
            error.add("Email");
        if (bd_n.length != 0)
            error.add("Nif");
        if (bd_t.length != 0)
            error.add("Telemovel");

        if (error.size() > 1) return error;

        List<String> success = new ArrayList<>();
        success.add("OK");

        //Decode from frontend and Encode to DB


        String client_password = c.getPassword();
        //client_password = decodePassword(client_password); // PROD : ADD THIS Decode encrypted password from frontend

        String new_Password = new BCryptPasswordEncoder(11).encode(client_password);
        c.setPassword(new_Password);

        /* PROD : ADD THIS


        try {
            ClienteDAO.save(c);
            Log.i(TAG,"Cliente Saved Succesfully");
            return success;
        } catch (PersistentException e) {
            e.printStackTrace();
            Log.e(TAG,"Error Saving Cliente");
        }

        //error.add("BD");
        //return  error;
         */
        return success; // PROD: DELETE THIS

    }

    public static Cliente parseUtilizadorJSON(String json) throws Exception {
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
            /* PROD: ADD THIS
            numT = obj.getLong("telemovel");
            distrito = obj.getString("distrito");
            concelho = obj.getString("concelho");
            freguesia = obj.getString("freguesia");
            morada =  obj.getString("morada");
            nif =  obj.getLong("nif");
             */
            return buildCliente(nome, email, password, numT, distrito, concelho, freguesia, morada, nif);
        } catch (Exception e) {
            Log.e(TAG, "Missing Fields on JSON");
            throw new Exception("Missing Fields");

        }
    }


    public static String parseEmailCliJSON(String body) throws Exception {

        JSONObject obj = new JSONObject(body);
        try {
            String email = "";
            email = obj.getString("email_cli");
            return email;
        } catch (Exception e) {
            Log.e(TAG, "Missing Fields on JSON");
        }
        return "ERROR";

    }



    public static Cliente getClientebyEmail(String email){
        String q = "Email = '" + email + "'";
        try {
            Cliente c = ClienteDAO.listClienteByQuery(q,"Email")[0];
            return c;
        }catch (PersistentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decodePassword(String password) {
        String encodedBase64Key = Crypt.encodeKey(SECRETKEY);
        return Crypt.decrypt(password, encodedBase64Key);

    }

    @Bean
    public static MyProfileResponse checkprofile(String email) {
        String q = "Email = '" + email + "'";
        Cliente[] clientes;
        try {
            clientes = ClienteDAO.listClienteByQuery(q, "Email");
            if (clientes.length > 0) {
                Cliente c = clientes[0];
                MyProfileResponse pr = new MyProfileResponse(c.getNome(), c.getEmail(), c.getNumTelemovel(), c.getNif(), c.getMorada(), c.getFreguesia(), c.getConcelho(), c.getDistrito());
                Log.i(TAG, "Cliente profile sent");
                return pr;
            }
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static MyProfileResponse parseUpdateProfile(String body) {
        JSONObject jsonObject = new JSONObject(body);
        String name = "";
        long nrTelm = 0;
        String morada = "";
        String freg = "";
        String conc = "";
        String distrito = "";
        try {
            name = jsonObject.getString("nome");
            nrTelm = jsonObject.getLong("nrTelm");
            morada = jsonObject.getString("morada");
            freg = jsonObject.getString("freguesia");
            conc = jsonObject.getString("concelho");
            distrito = jsonObject.getString("distrito");
            if (name.equals("") || nrTelm == 0 || morada.equals("") || freg.equals("") || conc.equals("") || distrito.equals(""))
                return null;

            //Email cant be update, so is ""
            MyProfileResponse pr = new MyProfileResponse(name, "", nrTelm, 0, morada, freg, conc, distrito);
            return pr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<String> parsePasswordUpdate(String body) {
        //Pos 0: Current Passowrd
        //Pos 1: New Passowrd
        JSONObject jsonObject = new JSONObject(body);
        //verificar que password atual é esta e atualizar para a nova
        List<String> res = new ArrayList<>();
        try {
            String pw_atual = "";
            pw_atual = jsonObject.getString("pw_atual");
            String pw_nova = "";
            pw_nova = jsonObject.getString("pw_nova");
            res.add(pw_atual);
            res.add(pw_nova);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Bean
    public static ResponseEntity updatePassoword(String email, String pw_atual, String pw_nova) {
        String q = "Email = '" + email + "'";
        Cliente[] clientes;
        try {
            clientes = ClienteDAO.listClienteByQuery(q, "Email");
            if (clientes.length > 0) {
                Cliente c = (Cliente) clientes[0];
                String password_old = c.getPassword();
                //If current password not matches
                boolean same = new BCryptPasswordEncoder(11).matches(pw_atual,password_old);
                if (!same) {
                    Log.e(TAG, "Cliente current password doesn't match!");
                    ErrorResponse er = new ErrorResponse();
                    er.setLocalError("EditPassword");
                    er.addMsg("CurrentPassword");
                    return  ResponseEntity.badRequest().body(er);
                } else {
                    Log.i(TAG, "Cliente current password matches");
                    //If matches, lets update
                    int res = ClienteDAO.updateClientePW(email, pw_nova);
                    if (res == 1) {
                        Log.i(TAG, "Cliente password succssefully updated");
                        return ResponseEntity.ok().body("OK");
                    } else {
                        Log.e(TAG, "Cliente password update went wrong");
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
    public static ResponseEntity checkPrestadorProfile(String email_pres){
        String q = "Email = '" + email_pres + "'";
        try {
            Prestador[] pres = PrestadorDAO.listPrestadorByQuery(q, "Email");
            if (pres.length <= 0) {
                ErrorResponse er = new ErrorResponse();
                er.setLocalError("PrestadorProfile");
                er.addMsg("NotFound");
                Log.e(TAG,"Prestador profile not found");
                return ResponseEntity.badRequest().body(er);
            } else {
                Prestador c = pres[0];
                int id = c.getID();
                String query = "PrestadorID = ' " + id + "'";
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
                Avaliacao_Prestador[] avaliacao_prestadorss = Avaliacao_PrestadorDAO.listAvaliacao_PrestadorByQuery(query, "ID");
                List<Avaliacao_Prestador> avaliacao_prestadors = Arrays.asList(avaliacao_prestadorss);
                PrestadorProfResponse cli_prof = new PrestadorProfResponse(c.getNome(), c.getEmail(), c.getNumTelemovel(), c.getMorada(), c.getFreguesia(), c.getConcelho(), c.getDistrito(),c.getClassificacao(),c.getNumServicosRealizados(),c.getNumServicosCancelados(), avaliacao_prestadors);
                Log.i(TAG,"Prestador profile sent with success");
                return ResponseEntity.ok().body(cli_prof);
            }
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return (ResponseEntity) ResponseEntity.notFound();
    }


    public static List<String> parseAvaliacaofromPres(String body){
        List<String> res = new ArrayList<>();
        String email_cli = "";
        String classificacao = "";
        String opiniao = "";
        String idServ = "";
        JSONObject jsonObject = new JSONObject(body);
        try{
            email_cli = jsonObject.getString("email_cliente");
            classificacao = String.valueOf(jsonObject.getDouble("classificacao"));
            opiniao = jsonObject.getString("opiniao");
            idServ = jsonObject.getString("idServico");

            if(email_cli.equals("") || classificacao.equals("") || opiniao.equals("") || idServ.equals(""))
                return null;
            else{
                //pos 0 -> email_cli
                res.add(email_cli);
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
    public static int avaliar_cliente(String email_pres, List<String> request){
        String email_cli = request.get(0);
        double classificacao = Double.valueOf(request.get(1));
        String opiniao = request.get(2);
        int idServico = Integer.valueOf(request.get(3));

        try {
            Servico servico = ServicoDAO.getServicoByORMID(idServico);
            if(servico.getEstado() < ServicoState.CREATED.v())
                return -2;
            if(servico.getEstado() ==ServicoState.CLIENTDONE.v() || servico.getEstado() == ServicoState.EVALUATED.v())
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
        //Update Numero Servicos Realizados
        c.setNumServicosRealizados(c.getNumServicosRealizados() + 1);
        //Update Classificacao
        double nova_classificação = (c.getClassificacao() * c.getNumServicosRealizados() + classificacao)/(c.getNumServicosRealizados() + 1);
        c.setClassificacao(nova_classificação);
        try {
            ClienteDAO.save(c);
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
        Avaliacao_Cliente aval = new Avaliacao_Cliente();
        aval.setClassificacao(classificacao);
        aval.setOpiniao(opiniao);
        aval.setPrestador(p);
        try {
            c.avaliacoes.add(aval);
            ClienteDAO.save(c);
            //Avaliacao_ClienteDAO.save(aval);
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        try {
            Servico servico = ServicoDAO.getServicoByORMID(idServico);
            if(servico.getEstado() == ServicoState.CREATED.v()){
                servico.setEstado(ServicoState.CLIENTDONE.v());
                ServicoDAO.save(servico);
            }
            if(servico.getEstado() == ServicoState.PROVIDERDONE.v()){
                servico.setEstado(ServicoState.EVALUATED.v());
                ServicoDAO.save(servico);
            }
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        return 1;
    }
}