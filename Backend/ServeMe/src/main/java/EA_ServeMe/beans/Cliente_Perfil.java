package EA_ServeMe.beans;

import EA_ServeMe.responses.*;
import EA_ServeMe.util.*;
import org.json.JSONObject;
import org.orm.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import servico.*;
import utilizador.*;


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
            error.add("Email já está em uso");
        if (bd_n.length != 0)
            error.add("Nif já está em uso");
        if (bd_t.length != 0)
            error.add("Número Telemovel já está em uso");

        if (error.size() > 1) return error;

        List<String> success = new ArrayList<>();
        success.add("OK");

        //Decode from frontend and Encode to DB


        String client_password = c.getPassword();
        client_password = Cliente_Perfil.decodePassword(client_password); // PROD : ADD THIS Decode encrypted password from frontend

        String new_Password = new BCryptPasswordEncoder(11).encode(client_password);
        c.setPassword(new_Password);

        /* PROD : ADD THIS */


        try {
            ClienteDAO.save(c);
            //ClienteDAO.refresh(c);
            Log.i(TAG,"Cliente Saved Succesfully");
            return success;
        } catch (PersistentException e) {
            e.printStackTrace();
            Log.e(TAG,"Error Saving Cliente");
        }

        error.add("BD"); // PROD: ADD THIS
        return  error; // PROD: ADD THIS

       // return success; // PROD: DELETE THIS

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
            /* PROD: ADD THIS */
            numT = obj.getLong("telemovel");
            distrito = obj.getString("distrito");
            concelho = obj.getString("concelho");
            freguesia = obj.getString("freguesia");
            morada =  obj.getString("morada");
            nif =  obj.getLong("nif");

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
    public static PrestadorProfResponse checkPrestadorProfile(String email_pres,String email_cli){
        String q = "Email = '" + email_pres + "'";
        try {
            Prestador[] pres = PrestadorDAO.listPrestadorByQuery(q, "Email");
            if (pres.length <= 0) {
                ErrorResponse er = new ErrorResponse();
                er.setLocalError("PrestadorProfile");
                er.addMsg("NotFound");
                Log.e(TAG,"Prestador profile not found");
                return null;
            } else {
                Prestador p = pres[0];
                int id = p.getID();
                Cliente cli = getClientebyEmail(email_cli);
                String query = "PrestadorID = ' " + id + "'";
                // PROD: ADD THIS
                Proposta[] props = PropostaDAO.listPropostaByQuery(query,"ID");

                boolean can = false;

                for(Proposta pro : props){
                    if(pro.getPedido().getCliente().getEmail().equals(email_cli))
                        can = true;
                }

                if(props.length > 0 && can) {
                    Avaliacao_Prestador[] avaliacao_prestadorss = Avaliacao_PrestadorDAO.listAvaliacao_PrestadorByQuery(query, "ID");
                    List<AvaliacaoResponse> avaliacoes = new ArrayList<>();
                    for(Avaliacao_Prestador a: avaliacao_prestadorss){
                        AvaliacaoResponse ar = new AvaliacaoResponse(a.getCliente().getNome(),a.getClassificacao(),a.getOpiniao());
                        avaliacoes.add(ar);
                    }
                    PrestadorProfResponse pres_prof = new PrestadorProfResponse(p.getNome(), p.getEmail(), p.getNumTelemovel(), p.getMorada(), p.getFreguesia(), p.getConcelho(), p.getDistrito(),p.getClassificacao(),p.getNumServicosRealizados(),p.getNumServicosCancelados(), avaliacoes);
                    Log.i(TAG,"Prestador profile sent with success");
                    return pres_prof;
                 }
                else{
                Log.e(TAG,"Cant acess this profile from external ways");
                ErrorResponse er = new ErrorResponse();
                er.setLocalError("Cant acess this profile from external ways");
                return null;
                }
            }
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return null;
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
            if(servico.getEstado() == ServicoState.PROVIDERDONE.v() || servico.getEstado() == ServicoState.EVALUATED.v())
                return -1;

            Pedido pedido = servico.getPedido();
            if(servico.getEstado() == ServicoState.CREATED.v()){
                servico.setEstado(ServicoState.PROVIDERDONE.v());
                ServicoDAO.save(servico);
            }
            if(servico.getEstado() == ServicoState.CLIENTDONE.v()){
                servico.setEstado(ServicoState.EVALUATED.v());
                ServicoDAO.save(servico);
            }
            pedido.setEstado(PedidoState.DONE.v());
            PedidoDAO.save(pedido);

            Cliente c = Cliente_Perfil.getClientebyEmail(email_cli);
            Prestador p = Prestador_Perfil.getPrestadorbyEmail(email_pres);


            int t = c.getNumServicosRealizados();

            //Update Classificacao
            double nova_classificação = (c.getClassificacao() * t + classificacao)/(t + 1 );
            c.setClassificacao(nova_classificação);
            c.setNumServicosRealizados(t+1);
            Avaliacao_Cliente aval = new Avaliacao_Cliente();
            aval.setClassificacao(classificacao);
            aval.setOpiniao(opiniao);
            aval.setPrestador(p);
            c.avaliacoes.add(aval);

            ClienteDAO.save(c);

//            Refresh hibernate cache
            //ClienteDAO.refresh(c);
            //PedidoDAO.refresh(pedido);
            //ServicoDAO.refresh(servico);
            ClienteDAO.evict(c);
            PedidoDAO.evict(pedido);
            ServicoDAO.evict(servico);


        } catch (PersistentException e) {
            e.printStackTrace();
        }

        return 1;
    }
}