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

public class PrestadorCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final StringExpression nome;
	public final StringExpression email;
	public final StringExpression password;
	public final LongExpression numTelemovel;
	public final StringExpression distrito;
	public final StringExpression concelho;
	public final StringExpression freguesia;
	public final StringExpression morada;
	public final DoubleExpression classificacao;
	public final LongExpression nif;
	public final IntegerExpression numServicosRealizados;
	public final IntegerExpression numServicosCancelados;
	public final CollectionExpression avaliacoes;
	
	public PrestadorCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		nome = new StringExpression("nome", this);
		email = new StringExpression("email", this);
		password = new StringExpression("password", this);
		numTelemovel = new LongExpression("numTelemovel", this);
		distrito = new StringExpression("distrito", this);
		concelho = new StringExpression("concelho", this);
		freguesia = new StringExpression("freguesia", this);
		morada = new StringExpression("morada", this);
		classificacao = new DoubleExpression("classificacao", this);
		nif = new LongExpression("nif", this);
		numServicosRealizados = new IntegerExpression("numServicosRealizados", this);
		numServicosCancelados = new IntegerExpression("numServicosCancelados", this);
		avaliacoes = new CollectionExpression("ORM_Avaliacoes", this);
	}
	
	public PrestadorCriteria(PersistentSession session) {
		this(session.createCriteria(Prestador.class));
	}
	
	public PrestadorCriteria() throws PersistentException {
		this(utilizador.ServemePersistentManager.instance().getSession());
	}
	
	public utilizador.Avaliacao_PrestadorCriteria createAvaliacoesCriteria() {
		return new utilizador.Avaliacao_PrestadorCriteria(createCriteria("ORM_Avaliacoes"));
	}
	
	public Prestador uniquePrestador() {
		return (Prestador) super.uniqueResult();
	}
	
	public Prestador[] listPrestador() {
		java.util.List list = super.list();
		return (Prestador[]) list.toArray(new Prestador[list.size()]);
	}
}

