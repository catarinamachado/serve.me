/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: Tiago Fontes(Universidade do Minho)
 * License Type: Academic
 */
package categorias;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class ClasseDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final StringExpression nome;
	
	public ClasseDetachedCriteria() {
		super(categorias.Classe.class, categorias.ClasseCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		nome = new StringExpression("nome", this.getDetachedCriteria());
	}
	
	public ClasseDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, categorias.ClasseCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		nome = new StringExpression("nome", this.getDetachedCriteria());
	}
	
	public Classe uniqueClasse(PersistentSession session) {
		return (Classe) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Classe[] listClasse(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Classe[]) list.toArray(new Classe[list.size()]);
	}
}

