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

public class Avaliacao_PrestadorCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression clienteId;
	public final AssociationExpression cliente;
	public final StringExpression opiniao;
	public final DoubleExpression classificacao;
	
	public Avaliacao_PrestadorCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		clienteId = new IntegerExpression("cliente.ID", this);
		cliente = new AssociationExpression("cliente", this);
		opiniao = new StringExpression("opiniao", this);
		classificacao = new DoubleExpression("classificacao", this);
	}
	
	public Avaliacao_PrestadorCriteria(PersistentSession session) {
		this(session.createCriteria(Avaliacao_Prestador.class));
	}
	
	public Avaliacao_PrestadorCriteria() throws PersistentException {
		this(utilizador.ServemePersistentManager.instance().getSession());
	}
	
	public ClienteCriteria createClienteCriteria() {
		return new ClienteCriteria(createCriteria("cliente"));
	}
	
	public Avaliacao_Prestador uniqueAvaliacao_Prestador() {
		return (Avaliacao_Prestador) super.uniqueResult();
	}
	
	public Avaliacao_Prestador[] listAvaliacao_Prestador() {
		java.util.List list = super.list();
		return (Avaliacao_Prestador[]) list.toArray(new Avaliacao_Prestador[list.size()]);
	}
}

