/**
 * Licensee: Tiago Fontes(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class CreateTutorialMDAData {
	public void createTestData() throws PersistentException {
		PersistentTransaction t = class_diagram.pt.uminho.di.aa.TutorialMDAPersistentManager.instance().getSession().beginTransaction();
		try {
			class_diagram.pt.uminho.di.aa.Game class_DiagramptuminhodiaaGame = class_diagram.pt.uminho.di.aa.GameDAO.createGame();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : price, year
			class_diagram.pt.uminho.di.aa.GameDAO.save(class_DiagramptuminhodiaaGame);
			class_diagram.pt.uminho.di.aa.User class_DiagramptuminhodiaaUser = class_diagram.pt.uminho.di.aa.UserDAO.createUser();
			// Initialize the properties of the persistent object here
			class_diagram.pt.uminho.di.aa.UserDAO.save(class_DiagramptuminhodiaaUser);
			class_diagram.pt.uminho.di.aa.Platform class_DiagramptuminhodiaaPlatform = class_diagram.pt.uminho.di.aa.PlatformDAO.createPlatform();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : year
			class_diagram.pt.uminho.di.aa.PlatformDAO.save(class_DiagramptuminhodiaaPlatform);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			CreateTutorialMDAData createTutorialMDAData = new CreateTutorialMDAData();
			try {
				createTutorialMDAData.createTestData();
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
