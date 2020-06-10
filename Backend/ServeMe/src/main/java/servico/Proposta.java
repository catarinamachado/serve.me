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

public class Proposta {
	public Proposta() {
	}
	
	private int ID;
	
	private servico.Pedido pedido;
	
	private utilizador.Prestador prestador;
	
	private java.util.Date horaInicio;
	
	private double precoProposto;
	
	private int vencedora;
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setHoraInicio(java.util.Date value) {
		this.horaInicio = value;
	}
	
	public java.util.Date getHoraInicio() {
		return horaInicio;
	}
	
	public void setPrecoProposto(double value) {
		this.precoProposto = value;
	}
	
	public double getPrecoProposto() {
		return precoProposto;
	}
	
	public void setVencedora(int value) {
		this.vencedora = value;
	}
	
	public int getVencedora() {
		return vencedora;
	}
	
	public void setPrestador(utilizador.Prestador value) {
		this.prestador = value;
	}
	
	public utilizador.Prestador getPrestador() {
		return prestador;
	}
	
	public void setPedido(servico.Pedido value) {
		this.pedido = value;
	}
	
	public servico.Pedido getPedido() {
		return pedido;
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
