package EA_ServeMe.beans;

import EA_ServeMe.util.*;
import net.bytebuddy.asm.Advice;
import org.json.JSONObject;
import org.orm.PersistentException;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import utilizador.*;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Prestador_Perfil {

    private static final String SECRETKEY =  "servemencriptkey";
    private static final String TAG =  "[PRESTADORPERFIL]";
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
             error.add("Email");
        if (bd_n.length != 0)
            error.add("Nif");
        if (bd_t.length != 0)
            error.add("Telemovel");

        if (error.size() > 1) return error;


        List<String> success = new ArrayList<>();
        success.add("OK");

        //Decode from frontend and Encode to DB

        String prestador_password = p.getPassword();
        //prestador_password = decodePassword(prestador_password);  //PROD: ADD THIS -- Função que faz decode da password vinda do frontend
        String new_Password = new BCryptPasswordEncoder(11).encode( prestador_password);
        p.setPassword(new_Password);
        /* PROD : ADD THIS
        try {
            PrestadorDAO.save(p);
            Log.i(TAG,"Prestador Saved Succesfully")
            return sucess;
        } catch (PersistentException e) {
            e.printStackTrace();
            Log.e(TAG,"Error Saving Prestador")
        }
         */
        //error.add("BD");
        //return  error;
        return success; // PROD: DELETE THIS

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
            /* PROD : ADD THIS
            numT = obj.getLong("telemovel");
            distrito = obj.getString("distrito");
            concelho = obj.getString("concelho");
            freguesia = obj.getString("freguesia");
            morada =  obj.getString("morada");
            nif =  obj.getLong("nif");
             */
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
        Cliente[] clientes;
        try {
            clientes = ClienteDAO.listClienteByQuery(q, "Email");
            if (clientes.length > 0) {
                Cliente c = (Cliente) clientes[0];
                String password_old = c.getPassword();
                //If current password not matches
                if (!password_old.equals(pw_atual)) {
                    Log.e(TAG, "Prestador current password doesn't match!");
                    ErrorResponse er = new ErrorResponse();
                    er.setLocalError("EditPassword");
                    er.addMsg("CurrentPassword");
                    return  ResponseEntity.badRequest().body(er);
                } else {
                    Log.i(TAG, "Prestador current password matches");
                    //If matches, lets update
                    int res = ClienteDAO.updateClientePW(email, pw_nova);
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
    public static ResponseEntity checkClienteProfile(String email_cli){
        String q = "Email = '" + email_cli + "'";
        try {
            Cliente[] cli = ClienteDAO.listClienteByQuery(q, "Email");
            if (cli.length <= 0) {
                ErrorResponse er = new ErrorResponse();
                er.setLocalError("Cliente not found");
                Log.e(TAG,"Cliente profile not found");
                return ResponseEntity.badRequest().body(er);
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
                Avaliacao_Cliente[] avaliacao_clientess = Avaliacao_ClienteDAO.listAvaliacao_ClienteByQuery(query, "ID");
                List<Avaliacao_Cliente> avaliacao_clientes = Arrays.asList(avaliacao_clientess);
                ClienteProfResponse cli_prof = new ClienteProfResponse(c.getNome(), c.getEmail(), c.getNumTelemovel(), c.getMorada(), c.getFreguesia(), c.getConcelho(), c.getDistrito(),c.getClassificacao(),c.getNumServicosRealizados(),c.getNumServicosCancelados(), avaliacao_clientes);
                Log.i(TAG,"Cliente profile sent with success");
                return ResponseEntity.ok().body(cli_prof);
            }
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return (ResponseEntity) ResponseEntity.notFound();
    }


}
