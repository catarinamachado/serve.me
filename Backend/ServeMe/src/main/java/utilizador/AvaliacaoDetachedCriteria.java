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

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class AvaliacaoDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final StringExpression opiniao;
	public final DoubleExpression classificacao;
	
	public AvaliacaoDetachedCriteria() {
		super(utilizador.Avaliacao.class, utilizador.AvaliacaoCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		opiniao = new StringExpression("opiniao", this.getDetachedCriteria());
		classificacao = new DoubleExpression("classificacao", this.getDetachedCriteria());
	}
	
	public AvaliacaoDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, utilizador.AvaliacaoCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		opiniao = new StringExpression("opiniao", this.getDetachedCriteria());
		classificacao = new DoubleExpression("classificacao", this.getDetachedCriteria());
	}
	
	public Avaliacao uniqueAvaliacao(PersistentSession session) {
		return (Avaliacao) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Avaliacao[] listAvaliacao(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Avaliacao[]) list.toArray(new Avaliacao[list.size()]);
	}
}

