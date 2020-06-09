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
package categorias;

public class Categoria {
	public Categoria() {
	}
	
	private int ID;
	
	private categorias.Classe classe;
	
	private String nome;
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setNome(String value) {
		this.nome = value;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setClasse(categorias.Classe value) {
		this.classe = value;
	}
	
	public categorias.Classe getClasse() {
		return classe;
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
