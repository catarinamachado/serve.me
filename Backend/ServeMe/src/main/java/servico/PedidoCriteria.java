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

import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class PedidoCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression clienteId;
	public final AssociationExpression cliente;
	public final IntegerExpression categoriaId;
	public final AssociationExpression categoria;
	public final DoubleExpression precoHora;
	public final DateExpression data;
	public final DateExpression horaInicioDisp;
	public final DateExpression horaFimDisp;
	public final DoubleExpression duracao;
	public final IntegerExpression estado;
	public final StringExpression descricao;
	
	public PedidoCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		clienteId = new IntegerExpression("cliente.ID", this);
		cliente = new AssociationExpression("cliente", this);
		categoriaId = new IntegerExpression("categoria.ID", this);
		categoria = new AssociationExpression("categoria", this);
		precoHora = new DoubleExpression("precoHora", this);
		data = new DateExpression("data", this);
		horaInicioDisp = new DateExpression("horaInicioDisp", this);
		horaFimDisp = new DateExpression("horaFimDisp", this);
		duracao = new DoubleExpression("duracao", this);
		estado = new IntegerExpression("estado", this);
		descricao = new StringExpression("descricao", this);
	}
	
	public PedidoCriteria(PersistentSession session) {
		this(session.createCriteria(Pedido.class));
	}
	
	public PedidoCriteria() throws PersistentException {
		this(utilizador.ServemePersistentManager.instance().getSession());
	}
	
	public utilizador.ClienteCriteria createClienteCriteria() {
		return new utilizador.ClienteCriteria(createCriteria("cliente"));
	}
	
	public categorias.CategoriaCriteria createCategoriaCriteria() {
		return new categorias.CategoriaCriteria(createCriteria("categoria"));
	}
	
	public Pedido uniquePedido() {
		return (Pedido) super.uniqueResult();
	}
	
	public Pedido[] listPedido() {
		java.util.List list = super.list();
		return (Pedido[]) list.toArray(new Pedido[list.size()]);
	}
}

