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

public class AvaliacaoCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final StringExpression opiniao;
	public final DoubleExpression classificacao;
	
	public AvaliacaoCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		opiniao = new StringExpression("opiniao", this);
		classificacao = new DoubleExpression("classificacao", this);
	}
	
	public AvaliacaoCriteria(PersistentSession session) {
		this(session.createCriteria(Avaliacao.class));
	}
	
	public AvaliacaoCriteria() throws PersistentException {
		this(utilizador.ServemePersistentManager.instance().getSession());
	}
	
	public Avaliacao uniqueAvaliacao() {
		return (Avaliacao) super.uniqueResult();
	}
	
	public Avaliacao[] listAvaliacao() {
		java.util.List list = super.list();
		return (Avaliacao[]) list.toArray(new Avaliacao[list.size()]);
	}
}

