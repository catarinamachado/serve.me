/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: João Costa(Universidade do Minho)
 * License Type: Academic
 */
package utilizador;

import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import java.util.List;

public class Informacao_pessoalDAO {
	public static Informacao_pessoal loadInformacao_pessoalByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return loadInformacao_pessoalByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Informacao_pessoal getInformacao_pessoalByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return getInformacao_pessoalByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Informacao_pessoal loadInformacao_pessoalByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return loadInformacao_pessoalByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Informacao_pessoal getInformacao_pessoalByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return getInformacao_pessoalByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Informacao_pessoal loadInformacao_pessoalByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Informacao_pessoal) session.load(utilizador.Informacao_pessoal.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Informacao_pessoal getInformacao_pessoalByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Informacao_pessoal) session.get(utilizador.Informacao_pessoal.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Informacao_pessoal loadInformacao_pessoalByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Informacao_pessoal) session.load(utilizador.Informacao_pessoal.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Informacao_pessoal getInformacao_pessoalByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Informacao_pessoal) session.get(utilizador.Informacao_pessoal.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryInformacao_pessoal(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return queryInformacao_pessoal(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryInformacao_pessoal(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return queryInformacao_pessoal(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Informacao_pessoal[] listInformacao_pessoalByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return listInformacao_pessoalByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Informacao_pessoal[] listInformacao_pessoalByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return listInformacao_pessoalByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryInformacao_pessoal(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From utilizador.Informacao_pessoal as Informacao_pessoal");
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
	
	public static List queryInformacao_pessoal(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From utilizador.Informacao_pessoal as Informacao_pessoal");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Informacao_pessoal", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Informacao_pessoal[] listInformacao_pessoalByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryInformacao_pessoal(session, condition, orderBy);
			return (Informacao_pessoal[]) list.toArray(new Informacao_pessoal[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Informacao_pessoal[] listInformacao_pessoalByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryInformacao_pessoal(session, condition, orderBy, lockMode);
			return (Informacao_pessoal[]) list.toArray(new Informacao_pessoal[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Informacao_pessoal loadInformacao_pessoalByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return loadInformacao_pessoalByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Informacao_pessoal loadInformacao_pessoalByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return loadInformacao_pessoalByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Informacao_pessoal loadInformacao_pessoalByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Informacao_pessoal[] informacao_pessoals = listInformacao_pessoalByQuery(session, condition, orderBy);
		if (informacao_pessoals != null && informacao_pessoals.length > 0)
			return informacao_pessoals[0];
		else
			return null;
	}
	
	public static Informacao_pessoal loadInformacao_pessoalByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Informacao_pessoal[] informacao_pessoals = listInformacao_pessoalByQuery(session, condition, orderBy, lockMode);
		if (informacao_pessoals != null && informacao_pessoals.length > 0)
			return informacao_pessoals[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateInformacao_pessoalByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return iterateInformacao_pessoalByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateInformacao_pessoalByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return iterateInformacao_pessoalByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateInformacao_pessoalByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From utilizador.Informacao_pessoal as Informacao_pessoal");
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
	
	public static java.util.Iterator iterateInformacao_pessoalByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From utilizador.Informacao_pessoal as Informacao_pessoal");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Informacao_pessoal", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Informacao_pessoal createInformacao_pessoal() {
		return new utilizador.Informacao_pessoal();
	}
	
	public static boolean save(utilizador.Informacao_pessoal informacao_pessoal) throws PersistentException {
		try {
			utilizador.ServemePersistentManager.instance().saveObject(informacao_pessoal);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(utilizador.Informacao_pessoal informacao_pessoal) throws PersistentException {
		try {
			utilizador.ServemePersistentManager.instance().deleteObject(informacao_pessoal);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(utilizador.Informacao_pessoal informacao_pessoal) throws PersistentException {
		try {
			utilizador.ServemePersistentManager.instance().getSession().refresh(informacao_pessoal);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(utilizador.Informacao_pessoal informacao_pessoal) throws PersistentException {
		try {
			utilizador.ServemePersistentManager.instance().getSession().evict(informacao_pessoal);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Informacao_pessoal loadInformacao_pessoalByCriteria(Informacao_pessoalCriteria informacao_pessoalCriteria) {
		Informacao_pessoal[] informacao_pessoals = listInformacao_pessoalByCriteria(informacao_pessoalCriteria);
		if(informacao_pessoals == null || informacao_pessoals.length == 0) {
			return null;
		}
		return informacao_pessoals[0];
	}
	
	public static Informacao_pessoal[] listInformacao_pessoalByCriteria(Informacao_pessoalCriteria informacao_pessoalCriteria) {
		return informacao_pessoalCriteria.listInformacao_pessoal();
	}
}
