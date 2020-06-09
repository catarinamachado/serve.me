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
package servico;

import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import java.util.List;

public class ServicoDAO {
	public static Servico loadServicoByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return loadServicoByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Servico getServicoByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return getServicoByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Servico loadServicoByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return loadServicoByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Servico getServicoByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return getServicoByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Servico loadServicoByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Servico) session.load(servico.Servico.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Servico getServicoByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Servico) session.get(servico.Servico.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Servico loadServicoByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Servico) session.load(servico.Servico.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Servico getServicoByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Servico) session.get(servico.Servico.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryServico(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return queryServico(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryServico(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return queryServico(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Servico[] listServicoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return listServicoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Servico[] listServicoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return listServicoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryServico(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From servico.Servico as Servico");
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
	
	public static List queryServico(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From servico.Servico as Servico");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Servico", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Servico[] listServicoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryServico(session, condition, orderBy);
			return (Servico[]) list.toArray(new Servico[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Servico[] listServicoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryServico(session, condition, orderBy, lockMode);
			return (Servico[]) list.toArray(new Servico[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Servico loadServicoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return loadServicoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Servico loadServicoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return loadServicoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Servico loadServicoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Servico[] servicos = listServicoByQuery(session, condition, orderBy);
		if (servicos != null && servicos.length > 0)
			return servicos[0];
		else
			return null;
	}
	
	public static Servico loadServicoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Servico[] servicos = listServicoByQuery(session, condition, orderBy, lockMode);
		if (servicos != null && servicos.length > 0)
			return servicos[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateServicoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return iterateServicoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateServicoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return iterateServicoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateServicoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From servico.Servico as Servico");
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
	
	public static java.util.Iterator iterateServicoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From servico.Servico as Servico");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Servico", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Servico createServico() {
		return new servico.Servico();
	}
	
	public static boolean save(servico.Servico servico) throws PersistentException {
		try {
			utilizador.ServemePersistentManager.instance().saveObject(servico);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(servico.Servico servico) throws PersistentException {
		try {
			utilizador.ServemePersistentManager.instance().deleteObject(servico);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(servico.Servico servico) throws PersistentException {
		try {
			utilizador.ServemePersistentManager.instance().getSession().refresh(servico);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(servico.Servico servico) throws PersistentException {
		try {
			utilizador.ServemePersistentManager.instance().getSession().evict(servico);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Servico loadServicoByCriteria(ServicoCriteria servicoCriteria) {
		Servico[] servicos = listServicoByCriteria(servicoCriteria);
		if(servicos == null || servicos.length == 0) {
			return null;
		}
		return servicos[0];
	}
	
	public static Servico[] listServicoByCriteria(ServicoCriteria servicoCriteria) {
		return servicoCriteria.listServico();
	}
}
