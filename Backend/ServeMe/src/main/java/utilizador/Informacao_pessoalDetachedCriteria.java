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

public class Informacao_pessoalDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final StringExpression nome;
	public final StringExpression email;
	public final LongExpression numTelemovel;
	public final StringExpression morada;
	public final DoubleExpression classificacao;
	public final LongExpression nif;
	public final IntegerExpression tipo;
	public final IntegerExpression numServicosRealizados;
	public final IntegerExpression numServicosCancelados;
	public final CollectionExpression avaliacoes;
	
	public Informacao_pessoalDetachedCriteria() {
		super(utilizador.Informacao_pessoal.class, utilizador.Informacao_pessoalCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		nome = new StringExpression("nome", this.getDetachedCriteria());
		email = new StringExpression("email", this.getDetachedCriteria());
		numTelemovel = new LongExpression("numTelemovel", this.getDetachedCriteria());
		morada = new StringExpression("morada", this.getDetachedCriteria());
		classificacao = new DoubleExpression("classificacao", this.getDetachedCriteria());
		nif = new LongExpression("nif", this.getDetachedCriteria());
		tipo = new IntegerExpression("tipo", this.getDetachedCriteria());
		numServicosRealizados = new IntegerExpression("numServicosRealizados", this.getDetachedCriteria());
		numServicosCancelados = new IntegerExpression("numServicosCancelados", this.getDetachedCriteria());
		avaliacoes = new CollectionExpression("ORM_Avaliacoes", this.getDetachedCriteria());
	}
	
	public Informacao_pessoalDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, utilizador.Informacao_pessoalCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		nome = new StringExpression("nome", this.getDetachedCriteria());
		email = new StringExpression("email", this.getDetachedCriteria());
		numTelemovel = new LongExpression("numTelemovel", this.getDetachedCriteria());
		morada = new StringExpression("morada", this.getDetachedCriteria());
		classificacao = new DoubleExpression("classificacao", this.getDetachedCriteria());
		nif = new LongExpression("nif", this.getDetachedCriteria());
		tipo = new IntegerExpression("tipo", this.getDetachedCriteria());
		numServicosRealizados = new IntegerExpression("numServicosRealizados", this.getDetachedCriteria());
		numServicosCancelados = new IntegerExpression("numServicosCancelados", this.getDetachedCriteria());
		avaliacoes = new CollectionExpression("ORM_Avaliacoes", this.getDetachedCriteria());
	}
	
	public utilizador.AvaliacaoDetachedCriteria createAvaliacoesCriteria() {
		return new utilizador.AvaliacaoDetachedCriteria(createCriteria("ORM_Avaliacoes"));
	}
	
	public Informacao_pessoal uniqueInformacao_pessoal(PersistentSession session) {
		return (Informacao_pessoal) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Informacao_pessoal[] listInformacao_pessoal(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Informacao_pessoal[]) list.toArray(new Informacao_pessoal[list.size()]);
	}
}

