/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: Tiago Fontes(Universidade do Minho)
 * License Type: Academic
 */
package categorias;

import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import java.util.List;

public class ClasseDAO {
	public static Classe loadClasseByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return loadClasseByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Classe getClasseByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return getClasseByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Classe loadClasseByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return loadClasseByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Classe getClasseByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return getClasseByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Classe loadClasseByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Classe) session.load(categorias.Classe.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Classe getClasseByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Classe) session.get(categorias.Classe.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Classe loadClasseByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Classe) session.load(categorias.Classe.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Classe getClasseByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Classe) session.get(categorias.Classe.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryClasse(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return queryClasse(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryClasse(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return queryClasse(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Classe[] listClasseByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return listClasseByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Classe[] listClasseByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return listClasseByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryClasse(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From categorias.Classe as Classe");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryClasse(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From categorias.Classe as Classe");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Classe", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Classe[] listClasseByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryClasse(session, condition, orderBy);
			return (Classe[]) list.toArray(new Classe[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Classe[] listClasseByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryClasse(session, condition, orderBy, lockMode);
			return (Classe[]) list.toArray(new Classe[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Classe loadClasseByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return loadClasseByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Classe loadClasseByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return loadClasseByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Classe loadClasseByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Classe[] classes = listClasseByQuery(session, condition, orderBy);
		if (classes != null && classes.length > 0)
			return classes[0];
		else
			return null;
	}
	
	public static Classe loadClasseByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Classe[] classes = listClasseByQuery(session, condition, orderBy, lockMode);
		if (classes != null && classes.length > 0)
			return classes[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateClasseByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return iterateClasseByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateClasseByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return iterateClasseByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateClasseByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From categorias.Classe as Classe");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateClasseByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From categorias.Classe as Classe");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Classe", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Classe createClasse() {
		return new categorias.Classe();
	}
	
	public static boolean save(categorias.Classe classe) throws PersistentException {
		try {
			utilizador.ServemePersistentManager.instance().saveObject(classe);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(categorias.Classe classe) throws PersistentException {
		try {
			utilizador.ServemePersistentManager.instance().deleteObject(classe);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(categorias.Classe classe) throws PersistentException {
		try {
			utilizador.ServemePersistentManager.instance().getSession().refresh(classe);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(categorias.Classe classe) throws PersistentException {
		try {
			utilizador.ServemePersistentManager.instance().getSession().evict(classe);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Classe loadClasseByCriteria(ClasseCriteria classeCriteria) {
		Classe[] classes = listClasseByCriteria(classeCriteria);
		if(classes == null || classes.length == 0) {
			return null;
		}
		return classes[0];
	}
	
	public static Classe[] listClasseByCriteria(ClasseCriteria classeCriteria) {
		return classeCriteria.listClasse();
	}
}
