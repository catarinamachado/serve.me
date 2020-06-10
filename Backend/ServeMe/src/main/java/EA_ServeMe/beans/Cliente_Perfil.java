package EA_ServeMe.beans;

import EA_ServeMe.util.AuthResponse;
import EA_ServeMe.util.JwtUtil;
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

    @Bean
    public static  AuthResponse loginTokenPrestador(String email,String toke){
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

    private static Cliente buildCliente(String nome, String email, String password, long numT , String morada, long nif){
        Cliente c = new Cliente();
        c.setNome(nome);c.setEmail(email);c.setPassword(password);c.setNumTelemovel(numT);c.setClassificacao(5.0);
        c.setMorada(morada);c.setNif(nif);c.setNumServicosCancelados(0);c.setNumServicosRealizados(0);
        return c;
    }

    @Bean
    public static List<String> registerCliente(String nome, String email, String password, long numT , String morada, long nif){
        //TODO: put password decoder from frontend

        Cliente c = buildCliente(nome,email,password,numT,morada,nif);
        String new_Password = new BCryptPasswordEncoder(11).encode( c.getPassword());
        c.setPassword(new_Password);

        try {
            ClienteDAO.save(c);
            List<String> r = new ArrayList();
            r.add(c.getEmail()); r.add(c.getPassword());
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();

    }

    // TODO: -Realizar operação de save de um prestador



}
