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

public class Avaliacao_ClienteDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression prestadorId;
	public final AssociationExpression prestador;
	public final StringExpression opiniao;
	public final DoubleExpression classificacao;
	
	public Avaliacao_ClienteDetachedCriteria() {
		super(utilizador.Avaliacao_Cliente.class, utilizador.Avaliacao_ClienteCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		prestadorId = new IntegerExpression("prestador.ID", this.getDetachedCriteria());
		prestador = new AssociationExpression("prestador", this.getDetachedCriteria());
		opiniao = new StringExpression("opiniao", this.getDetachedCriteria());
		classificacao = new DoubleExpression("classificacao", this.getDetachedCriteria());
	}
	
	public Avaliacao_ClienteDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, utilizador.Avaliacao_ClienteCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		prestadorId = new IntegerExpression("prestador.ID", this.getDetachedCriteria());
		prestador = new AssociationExpression("prestador", this.getDetachedCriteria());
		opiniao = new StringExpression("opiniao", this.getDetachedCriteria());
		classificacao = new DoubleExpression("classificacao", this.getDetachedCriteria());
	}
	
	public PrestadorDetachedCriteria createPrestadorCriteria() {
		return new PrestadorDetachedCriteria(createCriteria("prestador"));
	}
	
	public Avaliacao_Cliente uniqueAvaliacao_Cliente(PersistentSession session) {
		return (Avaliacao_Cliente) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Avaliacao_Cliente[] listAvaliacao_Cliente(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Avaliacao_Cliente[]) list.toArray(new Avaliacao_Cliente[list.size()]);
	}
}

