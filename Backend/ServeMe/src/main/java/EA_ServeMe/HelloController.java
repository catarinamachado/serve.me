package EA_ServeMe;


import class_diagram.pt.uminho.di.aa.User;
import class_diagram.pt.uminho.di.aa.UserDAO;
import org.orm.PersistentException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class HelloController {

        @RequestMapping("/")
        public String index() {
            try {
                User u = UserDAO.getUserByORMID(1);
                return "GANDA MALHA. CHAMA-Se" + u.getName();

            } catch (PersistentException e) {
                e.printStackTrace();
            }

            return "NAO O PESQUEI";
            //return "Greetings from Spring Boot!";
        }

    @RequestMapping("/utili")
    public List<User> utili() {
        //List
        try {
            List<User> utis = Arrays.asList(UserDAO.listUserByQuery("id > 0 ", "ID"));
            System.out.println("ELES SAO " + utis.size());
            return utis;
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return null;
    }

        
 }
