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

public class Avaliacao_PrestadorDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression clienteId;
	public final AssociationExpression cliente;
	public final StringExpression opiniao;
	public final DoubleExpression classificacao;
	
	public Avaliacao_PrestadorDetachedCriteria() {
		super(utilizador.Avaliacao_Prestador.class, utilizador.Avaliacao_PrestadorCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		clienteId = new IntegerExpression("cliente.ID", this.getDetachedCriteria());
		cliente = new AssociationExpression("cliente", this.getDetachedCriteria());
		opiniao = new StringExpression("opiniao", this.getDetachedCriteria());
		classificacao = new DoubleExpression("classificacao", this.getDetachedCriteria());
	}
	
	public Avaliacao_PrestadorDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, utilizador.Avaliacao_PrestadorCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		clienteId = new IntegerExpression("cliente.ID", this.getDetachedCriteria());
		cliente = new AssociationExpression("cliente", this.getDetachedCriteria());
		opiniao = new StringExpression("opiniao", this.getDetachedCriteria());
		classificacao = new DoubleExpression("classificacao", this.getDetachedCriteria());
	}
	
	public ClienteDetachedCriteria createClienteCriteria() {
		return new ClienteDetachedCriteria(createCriteria("cliente"));
	}
	
	public Avaliacao_Prestador uniqueAvaliacao_Prestador(PersistentSession session) {
		return (Avaliacao_Prestador) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Avaliacao_Prestador[] listAvaliacao_Prestador(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Avaliacao_Prestador[]) list.toArray(new Avaliacao_Prestador[list.size()]);
	}
}

