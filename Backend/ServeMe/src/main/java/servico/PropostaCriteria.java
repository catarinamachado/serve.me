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

public class PropostaCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression pedidoId;
	public final AssociationExpression pedido;
	public final IntegerExpression prestadorId;
	public final AssociationExpression prestador;
	public final DateExpression horaInicio;
	public final DoubleExpression precoProposto;
	public final IntegerExpression vencedora;
	
	public PropostaCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		pedidoId = new IntegerExpression("pedido.ID", this);
		pedido = new AssociationExpression("pedido", this);
		prestadorId = new IntegerExpression("prestador.ID", this);
		prestador = new AssociationExpression("prestador", this);
		horaInicio = new DateExpression("horaInicio", this);
		precoProposto = new DoubleExpression("precoProposto", this);
		vencedora = new IntegerExpression("vencedora", this);
	}
	
	public PropostaCriteria(PersistentSession session) {
		this(session.createCriteria(Proposta.class));
	}
	
	public PropostaCriteria() throws PersistentException {
		this(utilizador.ServemePersistentManager.instance().getSession());
	}
	
	public PedidoCriteria createPedidoCriteria() {
		return new PedidoCriteria(createCriteria("pedido"));
	}
	
	public utilizador.PrestadorCriteria createPrestadorCriteria() {
		return new utilizador.PrestadorCriteria(createCriteria("prestador"));
	}
	
	public Proposta uniqueProposta() {
		return (Proposta) super.uniqueResult();
	}
	
	public Proposta[] listProposta() {
		java.util.List list = super.list();
		return (Proposta[]) list.toArray(new Proposta[list.size()]);
	}
}

