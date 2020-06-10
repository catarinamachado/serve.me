package EA_ServeMe.service;

import org.orm.PersistentException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import utilizador.Cliente;
import utilizador.ClienteDAO;
import utilizador.Prestador;
import utilizador.PrestadorDAO;


import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        char tipo = email.charAt(0);
        email = email.substring(1);
        String q = "Email = '" + email + "'";

        switch (tipo) {
            case 'C':
                try {
                    Cliente[] clientes = ClienteDAO.listClienteByQuery(q, "Email");
                    if (clientes.length > 0) {
                        Cliente c = (Cliente) clientes[0];
                        return new org.springframework.security.core.userdetails.User(c.getEmail(), c.getPassword(), new ArrayList<>());
                    }
                } catch (PersistentException e) {
                }
                break;
            case 'P':
                try {
                    Prestador[] prestadores = PrestadorDAO.listPrestadorByQuery(q, "Email");
                    if (prestadores.length > 0) {
                        Prestador p = (Prestador) prestadores[0];
                        return new org.springframework.security.core.userdetails.User(p.getEmail(), p.getPassword(), new ArrayList<>());
                    }
                } catch (PersistentException e) {
                }
                break;


        }
        return new org.springframework.security.core.userdetails.User(null, null, new ArrayList<>());
    }
}
