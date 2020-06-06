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

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class CategoriaDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression classeId;
	public final AssociationExpression classe;
	public final StringExpression nome;
	
	public CategoriaDetachedCriteria() {
		super(categorias.Categoria.class, categorias.CategoriaCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		classeId = new IntegerExpression("classe.ID", this.getDetachedCriteria());
		classe = new AssociationExpression("classe", this.getDetachedCriteria());
		nome = new StringExpression("nome", this.getDetachedCriteria());
	}
	
	public CategoriaDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, categorias.CategoriaCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		classeId = new IntegerExpression("classe.ID", this.getDetachedCriteria());
		classe = new AssociationExpression("classe", this.getDetachedCriteria());
		nome = new StringExpression("nome", this.getDetachedCriteria());
	}
	
	public ClasseDetachedCriteria createClasseCriteria() {
		return new ClasseDetachedCriteria(createCriteria("classe"));
	}
	
	public Categoria uniqueCategoria(PersistentSession session) {
		return (Categoria) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Categoria[] listCategoria(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Categoria[]) list.toArray(new Categoria[list.size()]);
	}
}

