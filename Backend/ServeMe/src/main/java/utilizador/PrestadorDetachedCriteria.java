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

public class PrestadorDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression informacao_pessoalId;
	public final AssociationExpression informacao_pessoal;
	
	public PrestadorDetachedCriteria() {
		super(utilizador.Prestador.class, utilizador.PrestadorCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		informacao_pessoalId = new IntegerExpression("informacao_pessoal.ID", this.getDetachedCriteria());
		informacao_pessoal = new AssociationExpression("informacao_pessoal", this.getDetachedCriteria());
	}
	
	public PrestadorDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, utilizador.PrestadorCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		informacao_pessoalId = new IntegerExpression("informacao_pessoal.ID", this.getDetachedCriteria());
		informacao_pessoal = new AssociationExpression("informacao_pessoal", this.getDetachedCriteria());
	}
	
	public Informacao_pessoalDetachedCriteria createInformacao_pessoalCriteria() {
		return new Informacao_pessoalDetachedCriteria(createCriteria("informacao_pessoal"));
	}
	
	public Prestador uniquePrestador(PersistentSession session) {
		return (Prestador) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Prestador[] listPrestador(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Prestador[]) list.toArray(new Prestador[list.size()]);
	}
}

