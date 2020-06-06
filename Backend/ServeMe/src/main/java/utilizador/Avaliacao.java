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

public class Avaliacao {
	public Avaliacao() {
	}
	
	private int ID;
	
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
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
