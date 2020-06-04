import class_diagram.pt.uminho.di.aa.*;
import org.orm.PersistentException;

public class Main {

    public static void main(String[] args){

        //List
        try {
            User[] utis =  UserDAO.listUserByQuery("id > 0 ", "ID");
            System.out.println("ELES SAO " + utis.length);
        } catch (PersistentException e) {
            e.printStackTrace();
        }


    }


}
