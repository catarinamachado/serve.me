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
package servico;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class ServicoDetachedCriteria extends AbstractORMDetachedCriteria {
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
	
	public ServicoDetachedCriteria() {
		super(servico.Servico.class, servico.ServicoCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		clienteId = new IntegerExpression("cliente.ID", this.getDetachedCriteria());
		cliente = new AssociationExpression("cliente", this.getDetachedCriteria());
		prestadorId = new IntegerExpression("prestador.ID", this.getDetachedCriteria());
		prestador = new AssociationExpression("prestador", this.getDetachedCriteria());
		pedidoId = new IntegerExpression("pedido.ID", this.getDetachedCriteria());
		pedido = new AssociationExpression("pedido", this.getDetachedCriteria());
		propostaId = new IntegerExpression("proposta.ID", this.getDetachedCriteria());
		proposta = new AssociationExpression("proposta", this.getDetachedCriteria());
		estado = new IntegerExpression("estado", this.getDetachedCriteria());
	}
	
	public ServicoDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, servico.ServicoCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		clienteId = new IntegerExpression("cliente.ID", this.getDetachedCriteria());
		cliente = new AssociationExpression("cliente", this.getDetachedCriteria());
		prestadorId = new IntegerExpression("prestador.ID", this.getDetachedCriteria());
		prestador = new AssociationExpression("prestador", this.getDetachedCriteria());
		pedidoId = new IntegerExpression("pedido.ID", this.getDetachedCriteria());
		pedido = new AssociationExpression("pedido", this.getDetachedCriteria());
		propostaId = new IntegerExpression("proposta.ID", this.getDetachedCriteria());
		proposta = new AssociationExpression("proposta", this.getDetachedCriteria());
		estado = new IntegerExpression("estado", this.getDetachedCriteria());
	}
	
	public utilizador.ClienteDetachedCriteria createClienteCriteria() {
		return new utilizador.ClienteDetachedCriteria(createCriteria("cliente"));
	}
	
	public utilizador.PrestadorDetachedCriteria createPrestadorCriteria() {
		return new utilizador.PrestadorDetachedCriteria(createCriteria("prestador"));
	}
	
	public PedidoDetachedCriteria createPedidoCriteria() {
		return new PedidoDetachedCriteria(createCriteria("pedido"));
	}
	
	public PropostaDetachedCriteria createPropostaCriteria() {
		return new PropostaDetachedCriteria(createCriteria("proposta"));
	}
	
	public Servico uniqueServico(PersistentSession session) {
		return (Servico) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Servico[] listServico(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Servico[]) list.toArray(new Servico[list.size()]);
	}
}

