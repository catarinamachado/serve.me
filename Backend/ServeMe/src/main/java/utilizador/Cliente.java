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

public class Cliente {
	public Cliente() {
	}
	
	private int ID;
	
	private utilizador.Informacao_pessoal informacao_pessoal;
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setInformacao_pessoal(utilizador.Informacao_pessoal value) {
		this.informacao_pessoal = value;
	}
	
	public utilizador.Informacao_pessoal getInformacao_pessoal() {
		return informacao_pessoal;
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
