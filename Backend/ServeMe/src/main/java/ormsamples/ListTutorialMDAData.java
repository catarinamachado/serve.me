/**
 * Licensee: Tiago Fontes(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class ListTutorialMDAData {
	private static final int ROW_COUNT = 100;
	
	public void listTestData() throws PersistentException {
		System.out.println("Listing Game...");
		class_diagram.pt.uminho.di.aa.Game[] class_DiagramptuminhodiaaGames = class_diagram.pt.uminho.di.aa.GameDAO.listGameByQuery(null, null);
		int length = Math.min(class_DiagramptuminhodiaaGames.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(class_DiagramptuminhodiaaGames[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing User...");
		class_diagram.pt.uminho.di.aa.User[] class_DiagramptuminhodiaaUsers = class_diagram.pt.uminho.di.aa.UserDAO.listUserByQuery(null, null);
		length = Math.min(class_DiagramptuminhodiaaUsers.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(class_DiagramptuminhodiaaUsers[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Platform...");
		class_diagram.pt.uminho.di.aa.Platform[] class_DiagramptuminhodiaaPlatforms = class_diagram.pt.uminho.di.aa.PlatformDAO.listPlatformByQuery(null, null);
		length = Math.min(class_DiagramptuminhodiaaPlatforms.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(class_DiagramptuminhodiaaPlatforms[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
	}
	
	public void listByCriteria() throws PersistentException {
		System.out.println("Listing Game by Criteria...");
		class_diagram.pt.uminho.di.aa.GameCriteria class_DiagramptuminhodiaaGameCriteria = new class_diagram.pt.uminho.di.aa.GameCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//class_DiagramptuminhodiaaGameCriteria.ID.eq();
		class_DiagramptuminhodiaaGameCriteria.setMaxResults(ROW_COUNT);
		class_diagram.pt.uminho.di.aa.Game[] class_DiagramptuminhodiaaGames = class_DiagramptuminhodiaaGameCriteria.listGame();
		int length =class_DiagramptuminhodiaaGames== null ? 0 : Math.min(class_DiagramptuminhodiaaGames.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(class_DiagramptuminhodiaaGames[i]);
		}
		System.out.println(length + " Game record(s) retrieved."); 
		
		System.out.println("Listing User by Criteria...");
		class_diagram.pt.uminho.di.aa.UserCriteria class_DiagramptuminhodiaaUserCriteria = new class_diagram.pt.uminho.di.aa.UserCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//class_DiagramptuminhodiaaUserCriteria.ID.eq();
		class_DiagramptuminhodiaaUserCriteria.setMaxResults(ROW_COUNT);
		class_diagram.pt.uminho.di.aa.User[] class_DiagramptuminhodiaaUsers = class_DiagramptuminhodiaaUserCriteria.listUser();
		length =class_DiagramptuminhodiaaUsers== null ? 0 : Math.min(class_DiagramptuminhodiaaUsers.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(class_DiagramptuminhodiaaUsers[i]);
		}
		System.out.println(length + " User record(s) retrieved."); 
		
		System.out.println("Listing Platform by Criteria...");
		class_diagram.pt.uminho.di.aa.PlatformCriteria class_DiagramptuminhodiaaPlatformCriteria = new class_diagram.pt.uminho.di.aa.PlatformCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//class_DiagramptuminhodiaaPlatformCriteria.ID.eq();
		class_DiagramptuminhodiaaPlatformCriteria.setMaxResults(ROW_COUNT);
		class_diagram.pt.uminho.di.aa.Platform[] class_DiagramptuminhodiaaPlatforms = class_DiagramptuminhodiaaPlatformCriteria.listPlatform();
		length =class_DiagramptuminhodiaaPlatforms== null ? 0 : Math.min(class_DiagramptuminhodiaaPlatforms.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(class_DiagramptuminhodiaaPlatforms[i]);
		}
		System.out.println(length + " Platform record(s) retrieved."); 
		
	}
	
	public static void main(String[] args) {
		try {
			ListTutorialMDAData listTutorialMDAData = new ListTutorialMDAData();
			try {
				listTutorialMDAData.listTestData();
				//listTutorialMDAData.listByCriteria();
			}
			finally {
				class_diagram.pt.uminho.di.aa.TutorialMDAPersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
