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

public class Avaliacao_Prestador {
	public Avaliacao_Prestador() {
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == utilizador.ORMConstants.KEY_AVALIACAO_PRESTADOR_CLIENTE) {
			this.cliente = (utilizador.Cliente) owner;
		}
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private int ID;
	
	private utilizador.Cliente cliente;
	
	private String opiniao;
	
	private double classificacao;
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setOpiniao(String value) {
		this.opiniao = value;
	}
	
	public String getOpiniao() {
		return opiniao;
	}
	
	public void setClassificacao(double value) {
		this.classificacao = value;
	}
	
	public double getClassificacao() {
		return classificacao;
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
