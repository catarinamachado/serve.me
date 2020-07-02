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

import EA_ServeMe.util.Log;
import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import java.util.List;

public class PrestadorDAO {
	public static Prestador loadPrestadorByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return loadPrestadorByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prestador getPrestadorByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return getPrestadorByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prestador loadPrestadorByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return loadPrestadorByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prestador getPrestadorByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return getPrestadorByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prestador loadPrestadorByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Prestador) session.load(utilizador.Prestador.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prestador getPrestadorByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Prestador) session.get(utilizador.Prestador.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prestador loadPrestadorByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Prestador) session.load(utilizador.Prestador.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prestador getPrestadorByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Prestador) session.get(utilizador.Prestador.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryPrestador(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return queryPrestador(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryPrestador(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return queryPrestador(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prestador[] listPrestadorByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return listPrestadorByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prestador[] listPrestadorByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return listPrestadorByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryPrestador(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From utilizador.Prestador as Prestador");
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
	
	public static List queryPrestador(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From utilizador.Prestador as Prestador");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Prestador", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prestador[] listPrestadorByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryPrestador(session, condition, orderBy);
			return (Prestador[]) list.toArray(new Prestador[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prestador[] listPrestadorByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryPrestador(session, condition, orderBy, lockMode);
			return (Prestador[]) list.toArray(new Prestador[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prestador loadPrestadorByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return loadPrestadorByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prestador loadPrestadorByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return loadPrestadorByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prestador loadPrestadorByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Prestador[] prestadors = listPrestadorByQuery(session, condition, orderBy);
		if (prestadors != null && prestadors.length > 0)
			return prestadors[0];
		else
			return null;
	}
	
	public static Prestador loadPrestadorByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Prestador[] prestadors = listPrestadorByQuery(session, condition, orderBy, lockMode);
		if (prestadors != null && prestadors.length > 0)
			return prestadors[0];
		else
			return null;
	}
	
	public static java.util.Iterator iteratePrestadorByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return iteratePrestadorByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iteratePrestadorByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = utilizador.ServemePersistentManager.instance().getSession();
			return iteratePrestadorByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iteratePrestadorByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From utilizador.Prestador as Prestador");
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
	
	public static java.util.Iterator iteratePrestadorByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From utilizador.Prestador as Prestador");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Prestador", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prestador createPrestador() {
		return new utilizador.Prestador();
	}
	
	public static boolean save(utilizador.Prestador prestador) throws PersistentException {
		try {
			utilizador.ServemePersistentManager.instance().saveObject(prestador);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(utilizador.Prestador prestador) throws PersistentException {
		try {
			utilizador.ServemePersistentManager.instance().deleteObject(prestador);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(utilizador.Prestador prestador) throws PersistentException {
		try {
			utilizador.ServemePersistentManager.instance().getSession().refresh(prestador);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(utilizador.Prestador prestador) throws PersistentException {
		try {
			utilizador.ServemePersistentManager.instance().getSession().evict(prestador);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prestador loadPrestadorByCriteria(PrestadorCriteria prestadorCriteria) {
		Prestador[] prestadors = listPrestadorByCriteria(prestadorCriteria);
		if(prestadors == null || prestadors.length == 0) {
			return null;
		}
		return prestadors[0];
	}
	
	public static Prestador[] listPrestadorByCriteria(PrestadorCriteria prestadorCriteria) {
		return prestadorCriteria.listPrestador();
	}

	public static int updatePrestadorProf(String email, String nome, long nrTelm, String morada, String freg, String conc, String distrito){
		try{
			PersistentSession s = utilizador.ServemePersistentManager.instance().getSession();
			String q = "Update Prestador Set " + "Nome = '" + nome + "', " + "Morada = '" + morada + "', "+ " NumTelemovel = ' " + nrTelm + "'," + "Freguesia = '" + freg + "', " + "Concelho = '" + conc + "'," + "Distrito = '" + distrito + "' where " + "email = '" + email + "'";
			Query query = s.createQuery(q);
			int r = query.executeUpdate();
			if(r == 1){
				Log.i("[Prestador PROFILE]","Profile successfully updated");
				return 1;
			}
			else{
				Log.e("[Prestador PROFILE]","Profile update went wrong!");
				return 0;
			}
		} catch (PersistentException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int updatePrestadorPW(String email, String new_password){
		try{
			PersistentSession s = utilizador.ServemePersistentManager.instance().getSession();
			String q = "Update Prestador Set " + "Password = '" + new_password + "' where " + "email = '" + email + "'";
			Query query = s.createQuery(q);
			int r = query.executeUpdate();
			if(r == 1){
				Log.i("[Prestador PROFILE]","Password successfully changed");
				return 1;
			}
			else{
				Log.e("[Prestador PROFILE]","Password update went wrong!");
				return 0;
			}
		} catch (PersistentException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
