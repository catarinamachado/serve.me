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
package class_diagram.pt.uminho.di.aa;

import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class GameCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final StringExpression name;
	public final IntegerExpression year;
	public final DoubleExpression price;
	public final StringExpression description;
	
	public GameCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		name = new StringExpression("name", this);
		year = new IntegerExpression("year", this);
		price = new DoubleExpression("price", this);
		description = new StringExpression("description", this);
	}
	
	public GameCriteria(PersistentSession session) {
		this(session.createCriteria(Game.class));
	}
	
	public GameCriteria() throws PersistentException {
		this(class_diagram.pt.uminho.di.aa.TutorialMDAPersistentManager.instance().getSession());
	}
	
	public Game uniqueGame() {
		return (Game) super.uniqueResult();
	}
	
	public Game[] listGame() {
		java.util.List list = super.list();
		return (Game[]) list.toArray(new Game[list.size()]);
	}
}

