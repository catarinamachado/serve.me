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
package utilizador;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class PrestadorDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final StringExpression nome;
	public final StringExpression email;
	public final StringExpression password;
	public final LongExpression numTelemovel;
	public final StringExpression morada;
	public final DoubleExpression classificacao;
	public final LongExpression nif;
	public final IntegerExpression numServicosRealizados;
	public final IntegerExpression numServicosCancelados;
	public final CollectionExpression avaliacoes;
	
	public PrestadorDetachedCriteria() {
		super(utilizador.Prestador.class, utilizador.PrestadorCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		nome = new StringExpression("nome", this.getDetachedCriteria());
		email = new StringExpression("email", this.getDetachedCriteria());
		password = new StringExpression("password", this.getDetachedCriteria());
		numTelemovel = new LongExpression("numTelemovel", this.getDetachedCriteria());
		morada = new StringExpression("morada", this.getDetachedCriteria());
		classificacao = new DoubleExpression("classificacao", this.getDetachedCriteria());
		nif = new LongExpression("nif", this.getDetachedCriteria());
		numServicosRealizados = new IntegerExpression("numServicosRealizados", this.getDetachedCriteria());
		numServicosCancelados = new IntegerExpression("numServicosCancelados", this.getDetachedCriteria());
		avaliacoes = new CollectionExpression("ORM_Avaliacoes", this.getDetachedCriteria());
	}
	
	public PrestadorDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, utilizador.PrestadorCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		nome = new StringExpression("nome", this.getDetachedCriteria());
		email = new StringExpression("email", this.getDetachedCriteria());
		password = new StringExpression("password", this.getDetachedCriteria());
		numTelemovel = new LongExpression("numTelemovel", this.getDetachedCriteria());
		morada = new StringExpression("morada", this.getDetachedCriteria());
		classificacao = new DoubleExpression("classificacao", this.getDetachedCriteria());
		nif = new LongExpression("nif", this.getDetachedCriteria());
		numServicosRealizados = new IntegerExpression("numServicosRealizados", this.getDetachedCriteria());
		numServicosCancelados = new IntegerExpression("numServicosCancelados", this.getDetachedCriteria());
		avaliacoes = new CollectionExpression("ORM_Avaliacoes", this.getDetachedCriteria());
	}
	
	public utilizador.AvaliacaoDetachedCriteria createAvaliacoesCriteria() {
		return new utilizador.AvaliacaoDetachedCriteria(createCriteria("ORM_Avaliacoes"));
	}
	
	public Prestador uniquePrestador(PersistentSession session) {
		return (Prestador) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Prestador[] listPrestador(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Prestador[]) list.toArray(new Prestador[list.size()]);
	}
}

