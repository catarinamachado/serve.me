
import categorias.Classe;
import categorias.ClasseDAO;
import com.mysql.cj.xdevapi.Client;
import org.orm.PersistentException;
import utilizador.Cliente;
import utilizador.ClienteDAO;

public class Main {

    public static void main(String[] args){
        /*User user = new User();
        user.setEmail("miei@pt.com");
        user.setName("john");
        user.setPassword("pw");
        */
        Classe novo = new Classe();
        novo.setNome("Jardinagem");


        //Save
        try {
           //UserDAO.save(user);
            ClasseDAO.save(novo);
            System.out.println("Inseri o gajo!!"); //+ user.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }



        //Load
        try {
           Classe c = ClasseDAO.getClasseByORMID(1);
           //User uti =  UserDAO.getUserByORMID(1);
            System.out.println("Busquei o gajo!!" + c.getNome());
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        /*
        //List
        try {
            User[] utis =  UserDAO.listUserByQuery("id > 0 ", "ID");
            System.out.println("ELES SAO " + utis.length);
        } catch (PersistentException e) {
            e.printStackTrace();
        }

         */

    }


}
