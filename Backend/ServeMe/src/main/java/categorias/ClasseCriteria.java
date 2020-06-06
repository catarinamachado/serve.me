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
package categorias;

import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class ClasseCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final StringExpression nome;
	
	public ClasseCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		nome = new StringExpression("nome", this);
	}
	
	public ClasseCriteria(PersistentSession session) {
		this(session.createCriteria(Classe.class));
	}
	
	public ClasseCriteria() throws PersistentException {
		this(utilizador.ServemePersistentManager.instance().getSession());
	}
	
	public Classe uniqueClasse() {
		return (Classe) super.uniqueResult();
	}
	
	public Classe[] listClasse() {
		java.util.List list = super.list();
		return (Classe[]) list.toArray(new Classe[list.size()]);
	}
}

