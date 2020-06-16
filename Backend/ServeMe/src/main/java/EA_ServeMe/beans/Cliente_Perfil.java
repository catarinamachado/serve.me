package EA_ServeMe.beans;

import EA_ServeMe.util.AuthResponse;
import EA_ServeMe.util.Crypt;
import EA_ServeMe.util.JwtUtil;
import org.json.JSONObject;
import org.orm.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import utilizador.Cliente;
import utilizador.ClienteDAO;
import utilizador.Prestador;
import utilizador.PrestadorDAO;

import java.util.ArrayList;
import java.util.List;

public class Cliente_Perfil {

    private static final String SECRETKEY =  "servemencriptkey";

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

    private static Cliente buildCliente(String nome, String email, String password, long numT , String morada, long nif){
        Cliente c = new Cliente();
        c.setNome(nome);c.setEmail(email);c.setPassword(password);c.setNumTelemovel(numT);c.setClassificacao(5.0);
        c.setMorada(morada);c.setNif(nif);c.setNumServicosCancelados(0);c.setNumServicosRealizados(0);
        return c;
    }

    @Bean
    public static int registerCliente(Cliente c){ //(String nome, String email, String password, long numT , String morada, long nif){


        String client_password = c.getPassword();
        //client_password = decodePassword(client_password);

        String new_Password = new BCryptPasswordEncoder(11).encode( client_password);
        c.setPassword(new_Password);
        /*
        try {
            ClienteDAO.save(c);
            return 1;
        } catch (PersistentException e) {
            e.printStackTrace();
        }
         */
        //return 0;
        return 1; // DELETE THIS

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
            long numT = 0; // numT = obj.getLong("numT");
            String morada = ""; //morada =  obj.getString("morada");
            long nif = 0; //nif =  obj.getLong("nif");
            return buildCliente(nome,email,password,numT,morada,nif);
        }
        catch (Exception e){
            throw new Exception("Missing Fields");

        }
    }

    public static String decodePassword(String password) {
        String encodedBase64Key = Crypt.encodeKey(SECRETKEY);
        return  Crypt.decrypt(password,encodedBase64Key);

    }

}
