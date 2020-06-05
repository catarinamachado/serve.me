import class_diagram.pt.uminho.di.aa.*;
import org.orm.PersistentException;

public class Main {

    public static void main(String[] args){
        User user = new User();
        user.setEmail("miei@pt.com");
        user.setName("john");
        user.setPassword("pw");

        //Save
        try {
           UserDAO.save(user);
            System.out.println("Inseri o gajo!!" + user.getName());
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        /*

        //Load
        try {
           User uti =  UserDAO.getUserByORMID(1);
            System.out.println("Busquei o gajo!!" + uti.getName());
        } catch (PersistentException e) {
            e.printStackTrace();
        }

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
