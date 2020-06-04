/**
 * Licensee: Tiago Fontes(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class DeleteTutorialMDAData {
	public void deleteTestData() throws PersistentException {
		PersistentTransaction t = class_diagram.pt.uminho.di.aa.TutorialMDAPersistentManager.instance().getSession().beginTransaction();
		try {
			class_diagram.pt.uminho.di.aa.Game class_DiagramptuminhodiaaGame = class_diagram.pt.uminho.di.aa.GameDAO.loadGameByQuery(null, null);
			// Delete the persistent object
			class_diagram.pt.uminho.di.aa.GameDAO.delete(class_DiagramptuminhodiaaGame);
			class_diagram.pt.uminho.di.aa.User class_DiagramptuminhodiaaUser = class_diagram.pt.uminho.di.aa.UserDAO.loadUserByQuery(null, null);
			// Delete the persistent object
			class_diagram.pt.uminho.di.aa.UserDAO.delete(class_DiagramptuminhodiaaUser);
			class_diagram.pt.uminho.di.aa.Platform class_DiagramptuminhodiaaPlatform = class_diagram.pt.uminho.di.aa.PlatformDAO.loadPlatformByQuery(null, null);
			// Delete the persistent object
			class_diagram.pt.uminho.di.aa.PlatformDAO.delete(class_DiagramptuminhodiaaPlatform);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			DeleteTutorialMDAData deleteTutorialMDAData = new DeleteTutorialMDAData();
			try {
				deleteTutorialMDAData.deleteTestData();
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
