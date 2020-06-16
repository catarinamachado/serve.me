package EA_ServeMe.beans;

import EA_ServeMe.util.AuthResponse;
import EA_ServeMe.util.Crypt;
import org.json.JSONObject;
import org.orm.PersistentException;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import utilizador.Cliente;
import utilizador.Prestador;
import utilizador.PrestadorDAO;

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
    public static int registerPrestador(Prestador p){

        String prestador_password = p.getPassword();
        //prestador_password = decodePassword(prestador_password);

        String new_Password = new BCryptPasswordEncoder(11).encode( prestador_password);
        p.setPassword(new_Password);
        /*
        try {
            PrestadorDAO.save(c);
            return 1;
        } catch (PersistentException e) {
            e.printStackTrace();
        }
         */
        //return 0;
        return 1; // DELETE THIS

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
            long numT = 0; // numT = obj.getLong("numT");
            String morada = ""; //morada =  obj.getString("morada");
            long nif = 0; //nif =  obj.getLong("nif");
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
