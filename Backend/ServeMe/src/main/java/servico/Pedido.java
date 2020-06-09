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

public class Pedido {
	public Pedido() {
	}
	
	private int ID;
	
	private utilizador.Cliente cliente;
	
	private categorias.Categoria categoria;
	
	private double precoHora;
	
	private java.util.Date data;
	
	private java.util.Date horaInicioDisp;
	
	private java.util.Date horaFimDisp;
	
	private double duracao;
	
	private int estado;
	
	private String descricao;
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setPrecoHora(double value) {
		this.precoHora = value;
	}
	
	public double getPrecoHora() {
		return precoHora;
	}
	
	public void setData(java.util.Date value) {
		this.data = value;
	}
	
	public java.util.Date getData() {
		return data;
	}
	
	public void setHoraInicioDisp(java.util.Date value) {
		this.horaInicioDisp = value;
	}
	
	public java.util.Date getHoraInicioDisp() {
		return horaInicioDisp;
	}
	
	public void setHoraFimDisp(java.util.Date value) {
		this.horaFimDisp = value;
	}
	
	public java.util.Date getHoraFimDisp() {
		return horaFimDisp;
	}
	
	public void setDuracao(double value) {
		this.duracao = value;
	}
	
	public double getDuracao() {
		return duracao;
	}
	
	public void setEstado(int value) {
		this.estado = value;
	}
	
	public int getEstado() {
		return estado;
	}
	
	public void setDescricao(String value) {
		this.descricao = value;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setCategoria(categorias.Categoria value) {
		this.categoria = value;
	}
	
	public categorias.Categoria getCategoria() {
		return categoria;
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
