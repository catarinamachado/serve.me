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

import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class Informacao_pessoalCriteria extends AbstractORMCriteria {
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
	
	public Informacao_pessoalCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		nome = new StringExpression("nome", this);
		email = new StringExpression("email", this);
		numTelemovel = new LongExpression("numTelemovel", this);
		morada = new StringExpression("morada", this);
		classificacao = new DoubleExpression("classificacao", this);
		nif = new LongExpression("nif", this);
		tipo = new IntegerExpression("tipo", this);
		numServicosRealizados = new IntegerExpression("numServicosRealizados", this);
		numServicosCancelados = new IntegerExpression("numServicosCancelados", this);
		avaliacoes = new CollectionExpression("ORM_Avaliacoes", this);
	}
	
	public Informacao_pessoalCriteria(PersistentSession session) {
		this(session.createCriteria(Informacao_pessoal.class));
	}
	
	public Informacao_pessoalCriteria() throws PersistentException {
		this(utilizador.ServemePersistentManager.instance().getSession());
	}
	
	public utilizador.AvaliacaoCriteria createAvaliacoesCriteria() {
		return new utilizador.AvaliacaoCriteria(createCriteria("ORM_Avaliacoes"));
	}
	
	public Informacao_pessoal uniqueInformacao_pessoal() {
		return (Informacao_pessoal) super.uniqueResult();
	}
	
	public Informacao_pessoal[] listInformacao_pessoal() {
		java.util.List list = super.list();
		return (Informacao_pessoal[]) list.toArray(new Informacao_pessoal[list.size()]);
	}
}

