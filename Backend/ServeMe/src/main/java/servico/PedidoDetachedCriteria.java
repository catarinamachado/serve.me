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

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class PedidoDetachedCriteria extends AbstractORMDetachedCriteria {
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
	
	public PedidoDetachedCriteria() {
		super(servico.Pedido.class, servico.PedidoCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		clienteId = new IntegerExpression("cliente.ID", this.getDetachedCriteria());
		cliente = new AssociationExpression("cliente", this.getDetachedCriteria());
		categoriaId = new IntegerExpression("categoria.ID", this.getDetachedCriteria());
		categoria = new AssociationExpression("categoria", this.getDetachedCriteria());
		precoHora = new DoubleExpression("precoHora", this.getDetachedCriteria());
		data = new DateExpression("data", this.getDetachedCriteria());
		horaInicioDisp = new DateExpression("horaInicioDisp", this.getDetachedCriteria());
		horaFimDisp = new DateExpression("horaFimDisp", this.getDetachedCriteria());
		duracao = new DoubleExpression("duracao", this.getDetachedCriteria());
		estado = new IntegerExpression("estado", this.getDetachedCriteria());
		descricao = new StringExpression("descricao", this.getDetachedCriteria());
	}
	
	public PedidoDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, servico.PedidoCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		clienteId = new IntegerExpression("cliente.ID", this.getDetachedCriteria());
		cliente = new AssociationExpression("cliente", this.getDetachedCriteria());
		categoriaId = new IntegerExpression("categoria.ID", this.getDetachedCriteria());
		categoria = new AssociationExpression("categoria", this.getDetachedCriteria());
		precoHora = new DoubleExpression("precoHora", this.getDetachedCriteria());
		data = new DateExpression("data", this.getDetachedCriteria());
		horaInicioDisp = new DateExpression("horaInicioDisp", this.getDetachedCriteria());
		horaFimDisp = new DateExpression("horaFimDisp", this.getDetachedCriteria());
		duracao = new DoubleExpression("duracao", this.getDetachedCriteria());
		estado = new IntegerExpression("estado", this.getDetachedCriteria());
		descricao = new StringExpression("descricao", this.getDetachedCriteria());
	}
	
	public utilizador.ClienteDetachedCriteria createClienteCriteria() {
		return new utilizador.ClienteDetachedCriteria(createCriteria("cliente"));
	}
	
	public categorias.CategoriaDetachedCriteria createCategoriaCriteria() {
		return new categorias.CategoriaDetachedCriteria(createCriteria("categoria"));
	}
	
	public Pedido uniquePedido(PersistentSession session) {
		return (Pedido) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Pedido[] listPedido(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Pedido[]) list.toArray(new Pedido[list.size()]);
	}
}

