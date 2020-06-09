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
package servico;

import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class ServicoCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression clienteId;
	public final AssociationExpression cliente;
	public final IntegerExpression prestadorId;
	public final AssociationExpression prestador;
	public final IntegerExpression pedidoId;
	public final AssociationExpression pedido;
	public final IntegerExpression propostaId;
	public final AssociationExpression proposta;
	public final IntegerExpression estado;
	
	public ServicoCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		clienteId = new IntegerExpression("cliente.ID", this);
		cliente = new AssociationExpression("cliente", this);
		prestadorId = new IntegerExpression("prestador.ID", this);
		prestador = new AssociationExpression("prestador", this);
		pedidoId = new IntegerExpression("pedido.ID", this);
		pedido = new AssociationExpression("pedido", this);
		propostaId = new IntegerExpression("proposta.ID", this);
		proposta = new AssociationExpression("proposta", this);
		estado = new IntegerExpression("estado", this);
	}
	
	public ServicoCriteria(PersistentSession session) {
		this(session.createCriteria(Servico.class));
	}
	
	public ServicoCriteria() throws PersistentException {
		this(utilizador.ServemePersistentManager.instance().getSession());
	}
	
	public utilizador.ClienteCriteria createClienteCriteria() {
		return new utilizador.ClienteCriteria(createCriteria("cliente"));
	}
	
	public utilizador.PrestadorCriteria createPrestadorCriteria() {
		return new utilizador.PrestadorCriteria(createCriteria("prestador"));
	}
	
	public PedidoCriteria createPedidoCriteria() {
		return new PedidoCriteria(createCriteria("pedido"));
	}
	
	public PropostaCriteria createPropostaCriteria() {
		return new PropostaCriteria(createCriteria("proposta"));
	}
	
	public Servico uniqueServico() {
		return (Servico) super.uniqueResult();
	}
	
	public Servico[] listServico() {
		java.util.List list = super.list();
		return (Servico[]) list.toArray(new Servico[list.size()]);
	}
}

