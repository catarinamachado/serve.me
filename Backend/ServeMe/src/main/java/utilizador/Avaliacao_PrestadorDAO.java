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

public class Avaliacao_PrestadorDAO {
	public static Avaliacao_Prestador loadAvaliacao_PrestadorByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return loadAvaliacao_PrestadorByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Prestador getAvaliacao_PrestadorByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return getAvaliacao_PrestadorByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Prestador loadAvaliacao_PrestadorByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return loadAvaliacao_PrestadorByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Prestador getAvaliacao_PrestadorByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return getAvaliacao_PrestadorByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Prestador loadAvaliacao_PrestadorByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Avaliacao_Prestador) session.load(utilizador.Avaliacao_Prestador.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Prestador getAvaliacao_PrestadorByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Avaliacao_Prestador) session.get(utilizador.Avaliacao_Prestador.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Prestador loadAvaliacao_PrestadorByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Avaliacao_Prestador) session.load(utilizador.Avaliacao_Prestador.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Prestador getAvaliacao_PrestadorByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Avaliacao_Prestador) session.get(utilizador.Avaliacao_Prestador.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryAvaliacao_Prestador(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return queryAvaliacao_Prestador(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryAvaliacao_Prestador(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return queryAvaliacao_Prestador(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Prestador[] listAvaliacao_PrestadorByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return listAvaliacao_PrestadorByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Prestador[] listAvaliacao_PrestadorByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return listAvaliacao_PrestadorByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryAvaliacao_Prestador(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From utilizador.Avaliacao_Prestador as Avaliacao_Prestador");
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
	
	public static List queryAvaliacao_Prestador(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From utilizador.Avaliacao_Prestador as Avaliacao_Prestador");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Avaliacao_Prestador", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Prestador[] listAvaliacao_PrestadorByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryAvaliacao_Prestador(session, condition, orderBy);
			return (Avaliacao_Prestador[]) list.toArray(new Avaliacao_Prestador[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Prestador[] listAvaliacao_PrestadorByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryAvaliacao_Prestador(session, condition, orderBy, lockMode);
			return (Avaliacao_Prestador[]) list.toArray(new Avaliacao_Prestador[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Prestador loadAvaliacao_PrestadorByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return loadAvaliacao_PrestadorByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Prestador loadAvaliacao_PrestadorByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return loadAvaliacao_PrestadorByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Prestador loadAvaliacao_PrestadorByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Avaliacao_Prestador[] avaliacao_Prestadors = listAvaliacao_PrestadorByQuery(session, condition, orderBy);
		if (avaliacao_Prestadors != null && avaliacao_Prestadors.length > 0)
			return avaliacao_Prestadors[0];
		else
			return null;
	}
	
	public static Avaliacao_Prestador loadAvaliacao_PrestadorByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Avaliacao_Prestador[] avaliacao_Prestadors = listAvaliacao_PrestadorByQuery(session, condition, orderBy, lockMode);
		if (avaliacao_Prestadors != null && avaliacao_Prestadors.length > 0)
			return avaliacao_Prestadors[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateAvaliacao_PrestadorByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return iterateAvaliacao_PrestadorByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateAvaliacao_PrestadorByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return iterateAvaliacao_PrestadorByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateAvaliacao_PrestadorByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From utilizador.Avaliacao_Prestador as Avaliacao_Prestador");
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
	
	public static java.util.Iterator iterateAvaliacao_PrestadorByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From utilizador.Avaliacao_Prestador as Avaliacao_Prestador");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Avaliacao_Prestador", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Prestador createAvaliacao_Prestador() {
		return new utilizador.Avaliacao_Prestador();
	}
	
	public static boolean save(utilizador.Avaliacao_Prestador avaliacao_Prestador) throws PersistentException {
		try {
			utilizador.ServemePersistentManager.instance().saveObject(avaliacao_Prestador);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(utilizador.Avaliacao_Prestador avaliacao_Prestador) throws PersistentException {
		try {
			utilizador.ServemePersistentManager.instance().deleteObject(avaliacao_Prestador);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(utilizador.Avaliacao_Prestador avaliacao_Prestador) throws PersistentException {
		try {
			utilizador.ServemePersistentManager.instance().getSession().refresh(avaliacao_Prestador);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(utilizador.Avaliacao_Prestador avaliacao_Prestador) throws PersistentException {
		try {
			utilizador.ServemePersistentManager.instance().getSession().evict(avaliacao_Prestador);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Prestador loadAvaliacao_PrestadorByCriteria(Avaliacao_PrestadorCriteria avaliacao_PrestadorCriteria) {
		Avaliacao_Prestador[] avaliacao_Prestadors = listAvaliacao_PrestadorByCriteria(avaliacao_PrestadorCriteria);
		if(avaliacao_Prestadors == null || avaliacao_Prestadors.length == 0) {
			return null;
		}
		return avaliacao_Prestadors[0];
	}
	
	public static Avaliacao_Prestador[] listAvaliacao_PrestadorByCriteria(Avaliacao_PrestadorCriteria avaliacao_PrestadorCriteria) {
		return avaliacao_PrestadorCriteria.listAvaliacao_Prestador();
	}
}
