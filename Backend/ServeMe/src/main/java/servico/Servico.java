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

public class Servico {
	public Servico() {
	}
	
	private int ID;
	
	private utilizador.Cliente cliente;
	
	private utilizador.Prestador prestador;
	
	private servico.Pedido pedido;
	
	private servico.Proposta proposta;
	
	private int estado;
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setEstado(int value) {
		this.estado = value;
	}
	
	public int getEstado() {
		return estado;
	}
	
	public void setProposta(servico.Proposta value) {
		this.proposta = value;
	}
	
	public servico.Proposta getProposta() {
		return proposta;
	}
	
	public void setPedido(servico.Pedido value) {
		this.pedido = value;
	}
	
	public servico.Pedido getPedido() {
		return pedido;
	}
	
	public void setPrestador(utilizador.Prestador value) {
		this.prestador = value;
	}
	
	public utilizador.Prestador getPrestador() {
		return prestador;
	}
	
	public void setCliente(utilizador.Cliente value) {
		this.cliente = value;
	}
	
	public utilizador.Cliente getCliente() {
		return cliente;
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
