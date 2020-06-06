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

import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class PrestadorCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression informacao_pessoalId;
	public final AssociationExpression informacao_pessoal;
	
	public PrestadorCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		informacao_pessoalId = new IntegerExpression("informacao_pessoal.ID", this);
		informacao_pessoal = new AssociationExpression("informacao_pessoal", this);
	}
	
	public PrestadorCriteria(PersistentSession session) {
		this(session.createCriteria(Prestador.class));
	}
	
	public PrestadorCriteria() throws PersistentException {
		this(utilizador.ServemePersistentManager.instance().getSession());
	}
	
	public Informacao_pessoalCriteria createInformacao_pessoalCriteria() {
		return new Informacao_pessoalCriteria(createCriteria("informacao_pessoal"));
	}
	
	public Prestador uniquePrestador() {
		return (Prestador) super.uniqueResult();
	}
	
	public Prestador[] listPrestador() {
		java.util.List list = super.list();
		return (Prestador[]) list.toArray(new Prestador[list.size()]);
	}
}

