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

public class Avaliacao_ClienteCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression prestadorId;
	public final AssociationExpression prestador;
	public final StringExpression opiniao;
	public final DoubleExpression classificacao;
	
	public Avaliacao_ClienteCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		prestadorId = new IntegerExpression("prestador.ID", this);
		prestador = new AssociationExpression("prestador", this);
		opiniao = new StringExpression("opiniao", this);
		classificacao = new DoubleExpression("classificacao", this);
	}
	
	public Avaliacao_ClienteCriteria(PersistentSession session) {
		this(session.createCriteria(Avaliacao_Cliente.class));
	}
	
	public Avaliacao_ClienteCriteria() throws PersistentException {
		this(utilizador.ServemePersistentManager.instance().getSession());
	}
	
	public PrestadorCriteria createPrestadorCriteria() {
		return new PrestadorCriteria(createCriteria("prestador"));
	}
	
	public Avaliacao_Cliente uniqueAvaliacao_Cliente() {
		return (Avaliacao_Cliente) super.uniqueResult();
	}
	
	public Avaliacao_Cliente[] listAvaliacao_Cliente() {
		java.util.List list = super.list();
		return (Avaliacao_Cliente[]) list.toArray(new Avaliacao_Cliente[list.size()]);
	}
}

