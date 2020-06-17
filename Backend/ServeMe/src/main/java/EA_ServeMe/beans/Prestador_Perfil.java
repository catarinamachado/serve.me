package EA_ServeMe.beans;

import EA_ServeMe.util.AuthResponse;
import EA_ServeMe.util.Crypt;
import net.bytebuddy.asm.Advice;
import org.json.JSONObject;
import org.orm.PersistentException;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import utilizador.Cliente;
import utilizador.Prestador;
import utilizador.PrestadorDAO;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Prestador_Perfil {
    private static final String SECRETKEY =  "servemencriptkey";

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
            System.out.println("Impossível aceder à BD");
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
            System.out.println("[SUCCESS]: Prestador Added");
            return sucess;
        } catch (PersistentException e) {
            e.printStackTrace();
            System.out.println("[ERROR]: Prestador Not Saved");
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
            // numT = obj.getLong("numT"); // PROD : ADD THIS
            String morada = "";
            //morada =  obj.getString("morada"); // PROD : ADD THIS
            long nif = 0;
            //nif =  obj.getLong("nif"); // PROD : ADD THIS
            return buildPrestador(nome,email,password,numT,morada,nif);
        }
        catch (Exception e){
            throw new Exception("Missing Fields");

        }
    }

    private static Prestador buildPrestador(String nome, String email, String password, long numT , String morada, long nif){
        Prestador p = new Prestador();
        p.setNome(nome);p.setEmail(email);p.setPassword(password);p.setNumTelemovel(numT);p.setClassificacao(5.0);
        p.setMorada(morada);p.setNif(nif);p.setNumServicosCancelados(0);p.setNumServicosRealizados(0);
        return p;
    }

    public static String decodePassword(String password) {
        String encodedBase64Key = Crypt.encodeKey(SECRETKEY);
        return  Crypt.decrypt(password,encodedBase64Key);

    }
}
