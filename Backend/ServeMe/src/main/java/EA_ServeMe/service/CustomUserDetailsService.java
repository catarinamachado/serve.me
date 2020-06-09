package EA_ServeMe.service;

import org.orm.PersistentException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import utilizador.Cliente;
import utilizador.ClienteDAO;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Cliente c = new Cliente();
        try {
            c = ClienteDAO.getClienteByORMID(1);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return new org.springframework.security.core.userdetails.User(c.getInformacao_pessoal().getEmail(), c.getInformacao_pessoal().getPassword(), new ArrayList<>());
    }
}
