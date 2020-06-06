package EA_ServeMe;


import com.mysql.cj.xdevapi.Client;
import org.orm.PersistentException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import utilizador.Cliente;
import utilizador.ClienteDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class SimpleController {

        @RequestMapping("/")
        public String index() {

            try {
               // User u = UserDAO.getUserByORMID(1);
                return "GANDA MALHA. CHAMA-Se"; //+ u.getName();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return "NAO O PESQUEI";
            //return "Greetings from Spring Boot!";

        }

    @GetMapping("/utili")
    public List<String> utili() {
        //List
        try {
            //List<User> utis = Arrays.asList(UserDAO.listUserByQuery("id > 0 ", "ID"));
            //System.out.println("ELES SAO " + utis.size());
            return new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/newuser")
    Cliente newEmployee(@RequestBody Cliente newuser) {
        try {
            /*
            String name = newuser.getName();
            String email = newuser.getEmail();
            String password = newuser.getPassword();
            User u = new User();
            u.setName(name);
            u.setEmail(email);
            u.setPassword(password);
             */
            ClienteDAO.save(newuser);
            return newuser;
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return null;
    }
 }
