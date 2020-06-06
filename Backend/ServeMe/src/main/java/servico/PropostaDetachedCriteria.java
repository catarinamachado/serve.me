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

public class PropostaDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression pedidoId;
	public final AssociationExpression pedido;
	public final IntegerExpression prestadorId;
	public final AssociationExpression prestador;
	public final DateExpression horaInicio;
	public final DoubleExpression precoProposto;
	public final IntegerExpression vencedora;
	
	public PropostaDetachedCriteria() {
		super(servico.Proposta.class, servico.PropostaCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		pedidoId = new IntegerExpression("pedido.ID", this.getDetachedCriteria());
		pedido = new AssociationExpression("pedido", this.getDetachedCriteria());
		prestadorId = new IntegerExpression("prestador.ID", this.getDetachedCriteria());
		prestador = new AssociationExpression("prestador", this.getDetachedCriteria());
		horaInicio = new DateExpression("horaInicio", this.getDetachedCriteria());
		precoProposto = new DoubleExpression("precoProposto", this.getDetachedCriteria());
		vencedora = new IntegerExpression("vencedora", this.getDetachedCriteria());
	}
	
	public PropostaDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, servico.PropostaCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		pedidoId = new IntegerExpression("pedido.ID", this.getDetachedCriteria());
		pedido = new AssociationExpression("pedido", this.getDetachedCriteria());
		prestadorId = new IntegerExpression("prestador.ID", this.getDetachedCriteria());
		prestador = new AssociationExpression("prestador", this.getDetachedCriteria());
		horaInicio = new DateExpression("horaInicio", this.getDetachedCriteria());
		precoProposto = new DoubleExpression("precoProposto", this.getDetachedCriteria());
		vencedora = new IntegerExpression("vencedora", this.getDetachedCriteria());
	}
	
	public PedidoDetachedCriteria createPedidoCriteria() {
		return new PedidoDetachedCriteria(createCriteria("pedido"));
	}
	
	public utilizador.PrestadorDetachedCriteria createPrestadorCriteria() {
		return new utilizador.PrestadorDetachedCriteria(createCriteria("prestador"));
	}
	
	public Proposta uniqueProposta(PersistentSession session) {
		return (Proposta) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Proposta[] listProposta(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Proposta[]) list.toArray(new Proposta[list.size()]);
	}
}

