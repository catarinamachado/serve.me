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

public class Avaliacao_ClienteDAO {
	public static Avaliacao_Cliente loadAvaliacao_ClienteByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return loadAvaliacao_ClienteByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Cliente getAvaliacao_ClienteByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return getAvaliacao_ClienteByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Cliente loadAvaliacao_ClienteByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return loadAvaliacao_ClienteByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Cliente getAvaliacao_ClienteByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return getAvaliacao_ClienteByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Cliente loadAvaliacao_ClienteByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Avaliacao_Cliente) session.load(utilizador.Avaliacao_Cliente.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Cliente getAvaliacao_ClienteByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Avaliacao_Cliente) session.get(utilizador.Avaliacao_Cliente.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Cliente loadAvaliacao_ClienteByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Avaliacao_Cliente) session.load(utilizador.Avaliacao_Cliente.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Cliente getAvaliacao_ClienteByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Avaliacao_Cliente) session.get(utilizador.Avaliacao_Cliente.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryAvaliacao_Cliente(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return queryAvaliacao_Cliente(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryAvaliacao_Cliente(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return queryAvaliacao_Cliente(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Cliente[] listAvaliacao_ClienteByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return listAvaliacao_ClienteByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Cliente[] listAvaliacao_ClienteByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return listAvaliacao_ClienteByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryAvaliacao_Cliente(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From utilizador.Avaliacao_Cliente as Avaliacao_Cliente");
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
	
	public static List queryAvaliacao_Cliente(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From utilizador.Avaliacao_Cliente as Avaliacao_Cliente");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Avaliacao_Cliente", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Cliente[] listAvaliacao_ClienteByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryAvaliacao_Cliente(session, condition, orderBy);
			return (Avaliacao_Cliente[]) list.toArray(new Avaliacao_Cliente[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Cliente[] listAvaliacao_ClienteByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryAvaliacao_Cliente(session, condition, orderBy, lockMode);
			return (Avaliacao_Cliente[]) list.toArray(new Avaliacao_Cliente[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Cliente loadAvaliacao_ClienteByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return loadAvaliacao_ClienteByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Cliente loadAvaliacao_ClienteByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return loadAvaliacao_ClienteByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Cliente loadAvaliacao_ClienteByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Avaliacao_Cliente[] avaliacao_Clientes = listAvaliacao_ClienteByQuery(session, condition, orderBy);
		if (avaliacao_Clientes != null && avaliacao_Clientes.length > 0)
			return avaliacao_Clientes[0];
		else
			return null;
	}
	
	public static Avaliacao_Cliente loadAvaliacao_ClienteByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Avaliacao_Cliente[] avaliacao_Clientes = listAvaliacao_ClienteByQuery(session, condition, orderBy, lockMode);
		if (avaliacao_Clientes != null && avaliacao_Clientes.length > 0)
			return avaliacao_Clientes[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateAvaliacao_ClienteByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return iterateAvaliacao_ClienteByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateAvaliacao_ClienteByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return iterateAvaliacao_ClienteByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateAvaliacao_ClienteByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From utilizador.Avaliacao_Cliente as Avaliacao_Cliente");
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
	
	public static java.util.Iterator iterateAvaliacao_ClienteByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From utilizador.Avaliacao_Cliente as Avaliacao_Cliente");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Avaliacao_Cliente", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Cliente createAvaliacao_Cliente() {
		return new utilizador.Avaliacao_Cliente();
	}
	
	public static boolean save(utilizador.Avaliacao_Cliente avaliacao_Cliente) throws PersistentException {
		try {
			utilizador.ServemePersistentManager.instance().saveObject(avaliacao_Cliente);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(utilizador.Avaliacao_Cliente avaliacao_Cliente) throws PersistentException {
		try {
			utilizador.ServemePersistentManager.instance().deleteObject(avaliacao_Cliente);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(utilizador.Avaliacao_Cliente avaliacao_Cliente) throws PersistentException {
		try {
			utilizador.ServemePersistentManager.instance().getSession().refresh(avaliacao_Cliente);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(utilizador.Avaliacao_Cliente avaliacao_Cliente) throws PersistentException {
		try {
			utilizador.ServemePersistentManager.instance().getSession().evict(avaliacao_Cliente);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Avaliacao_Cliente loadAvaliacao_ClienteByCriteria(Avaliacao_ClienteCriteria avaliacao_ClienteCriteria) {
		Avaliacao_Cliente[] avaliacao_Clientes = listAvaliacao_ClienteByCriteria(avaliacao_ClienteCriteria);
		if(avaliacao_Clientes == null || avaliacao_Clientes.length == 0) {
			return null;
		}
		return avaliacao_Clientes[0];
	}
	
	public static Avaliacao_Cliente[] listAvaliacao_ClienteByCriteria(Avaliacao_ClienteCriteria avaliacao_ClienteCriteria) {
		return avaliacao_ClienteCriteria.listAvaliacao_Cliente();
	}
}
