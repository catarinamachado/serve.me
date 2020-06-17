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

public class Avaliacao_Cliente {
	public Avaliacao_Cliente() {
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == utilizador.ORMConstants.KEY_AVALIACAO_CLIENTE_PRESTADOR) {
			this.prestador = (utilizador.Prestador) owner;
		}
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private int ID;
	
	private utilizador.Prestador prestador;
	
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
	
	public void setPrestador(utilizador.Prestador value) {
		this.prestador = value;
	}
	
	public utilizador.Prestador getPrestador() {
		return prestador;
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
