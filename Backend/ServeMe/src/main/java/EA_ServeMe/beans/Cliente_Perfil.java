package EA_ServeMe.beans;

import EA_ServeMe.util.AuthResponse;
import EA_ServeMe.util.Crypt;
import EA_ServeMe.util.JwtUtil;
import EA_ServeMe.util.Log;
import org.json.JSONObject;
import org.orm.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import utilizador.Cliente;
import utilizador.ClienteDAO;

import java.util.ArrayList;
import java.util.List;

public class Cliente_Perfil {

    private static final String SECRETKEY =  "servemencriptkey";
    private static final String TAG =  "[CLIENTEPERFIL]";

    @Bean
    public static  AuthResponse loginTokenCliente(String email,String toke){
        String token =  toke;
        String q = "Email = '" + email + "'";
        Cliente[] clientes;
        try {
            clientes = ClienteDAO.listClienteByQuery(q,"Email");
            Cliente c = (Cliente) clientes[0];
            String nome = c.getNome();
            AuthResponse ar = new AuthResponse(token,nome);
            return  ar;
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Cliente buildCliente(String nome, String email, String password, long numT , String distrito, String concelho, String freguesia ,String morada, long nif){
        Cliente c = new Cliente();
        c.setNome(nome);c.setEmail(email);c.setPassword(password);c.setNumTelemovel(numT);c.setClassificacao(5.0);
        c.setDistrito(distrito); c.setConcelho(concelho);c.setFreguesia(freguesia);c.setMorada(morada);
        c.setNif(nif);c.setNumServicosCancelados(0);c.setNumServicosRealizados(0);
        return c;
    }

    @Bean
    public static List<String> registerCliente(Cliente c){ //(String nome, String email, String password, long numT , String morada, long nif){
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
            bd_e = ClienteDAO.listClienteByQuery(query_e,"Email");
            bd_n = ClienteDAO.listClienteByQuery(query_n,"Nif");
            bd_t = ClienteDAO.listClienteByQuery(query_t,"NumTelemovel");
        } catch (PersistentException e) {
            Log.e("[DB]", "Impossível aceder à BD");
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


        String client_password = c.getPassword();
        //client_password = decodePassword(client_password); // PROD : ADD THIS Decode encrypted password from frontend

        String new_Password = new BCryptPasswordEncoder(11).encode( client_password);
        c.setPassword(new_Password);

        /* PROD : ADD THIS
        try {
            ClienteDAO.save(c);
            Log.i(TAG,"Cliente Saved Succesfully")
            return success;
        } catch (PersistentException e) {
            e.printStackTrace();
            Log.e(TAG,"Error Saving Cliente")
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
            return buildCliente(nome,email,password,numT,distrito,concelho,freguesia,morada,nif);
        }
        catch (Exception e){
            Log.e(TAG,"Missing Fields on JSON");
            throw new Exception("Missing Fields");

        }
    }

    public static String decodePassword(String password) {
        String encodedBase64Key = Crypt.encodeKey(SECRETKEY);
        return  Crypt.decrypt(password,encodedBase64Key);

    }

}
