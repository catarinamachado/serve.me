/**
 * Licensee: Tiago Fontes(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class RetrieveAndUpdateTutorialMDAData {
	public void retrieveAndUpdateTestData() throws PersistentException {
		PersistentTransaction t = class_diagram.pt.uminho.di.aa.TutorialMDAPersistentManager.instance().getSession().beginTransaction();
		try {
			class_diagram.pt.uminho.di.aa.Game class_DiagramptuminhodiaaGame = class_diagram.pt.uminho.di.aa.GameDAO.loadGameByQuery(null, null);
			// Update the properties of the persistent object
			class_diagram.pt.uminho.di.aa.GameDAO.save(class_DiagramptuminhodiaaGame);
			class_diagram.pt.uminho.di.aa.User class_DiagramptuminhodiaaUser = class_diagram.pt.uminho.di.aa.UserDAO.loadUserByQuery(null, null);
			// Update the properties of the persistent object
			class_diagram.pt.uminho.di.aa.UserDAO.save(class_DiagramptuminhodiaaUser);
			class_diagram.pt.uminho.di.aa.Platform class_DiagramptuminhodiaaPlatform = class_diagram.pt.uminho.di.aa.PlatformDAO.loadPlatformByQuery(null, null);
			// Update the properties of the persistent object
			class_diagram.pt.uminho.di.aa.PlatformDAO.save(class_DiagramptuminhodiaaPlatform);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public void retrieveByCriteria() throws PersistentException {
		System.out.println("Retrieving Game by GameCriteria");
		class_diagram.pt.uminho.di.aa.GameCriteria class_DiagramptuminhodiaaGameCriteria = new class_diagram.pt.uminho.di.aa.GameCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//class_DiagramptuminhodiaaGameCriteria.ID.eq();
		System.out.println(class_DiagramptuminhodiaaGameCriteria.uniqueGame());
		
		System.out.println("Retrieving User by UserCriteria");
		class_diagram.pt.uminho.di.aa.UserCriteria class_DiagramptuminhodiaaUserCriteria = new class_diagram.pt.uminho.di.aa.UserCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//class_DiagramptuminhodiaaUserCriteria.ID.eq();
		System.out.println(class_DiagramptuminhodiaaUserCriteria.uniqueUser());
		
		System.out.println("Retrieving Platform by PlatformCriteria");
		class_diagram.pt.uminho.di.aa.PlatformCriteria class_DiagramptuminhodiaaPlatformCriteria = new class_diagram.pt.uminho.di.aa.PlatformCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//class_DiagramptuminhodiaaPlatformCriteria.ID.eq();
		System.out.println(class_DiagramptuminhodiaaPlatformCriteria.uniquePlatform());
		
	}
	
	
	public static void main(String[] args) {
		try {
			RetrieveAndUpdateTutorialMDAData retrieveAndUpdateTutorialMDAData = new RetrieveAndUpdateTutorialMDAData();
			try {
				retrieveAndUpdateTutorialMDAData.retrieveAndUpdateTestData();
				//retrieveAndUpdateTutorialMDAData.retrieveByCriteria();
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
