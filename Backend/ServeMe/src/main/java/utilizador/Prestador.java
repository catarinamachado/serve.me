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

public class Prestador {
	public Prestador() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == utilizador.ORMConstants.KEY_PRESTADOR_AVALIACOES) {
			return ORM_avaliacoes;
		}
		
		return null;
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	private int ID;
	
	private String nome;
	
	private String email;
	
	private String password;
	
	private long numTelemovel;
	
	private String distrito;
	
	private String concelho;
	
	private String freguesia;
	
	private String morada;
	
	private double classificacao;
	
	private long nif;
	
	private int numServicosRealizados;
	
	private int numServicosCancelados;
	
	private java.util.Set ORM_avaliacoes = new java.util.HashSet();
	
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
	
	public void setEmail(String value) {
		this.email = value;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPassword(String value) {
		this.password = value;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setNumTelemovel(long value) {
		this.numTelemovel = value;
	}
	
	public long getNumTelemovel() {
		return numTelemovel;
	}
	
	public void setDistrito(String value) {
		this.distrito = value;
	}
	
	public String getDistrito() {
		return distrito;
	}
	
	public void setConcelho(String value) {
		this.concelho = value;
	}
	
	public String getConcelho() {
		return concelho;
	}
	
	public void setFreguesia(String value) {
		this.freguesia = value;
	}
	
	public String getFreguesia() {
		return freguesia;
	}
	
	public void setMorada(String value) {
		this.morada = value;
	}
	
	public String getMorada() {
		return morada;
	}
	
	public void setClassificacao(double value) {
		this.classificacao = value;
	}
	
	public double getClassificacao() {
		return classificacao;
	}
	
	public void setNif(long value) {
		this.nif = value;
	}
	
	public long getNif() {
		return nif;
	}
	
	public void setNumServicosRealizados(int value) {
		this.numServicosRealizados = value;
	}
	
	public int getNumServicosRealizados() {
		return numServicosRealizados;
	}
	
	public void setNumServicosCancelados(int value) {
		this.numServicosCancelados = value;
	}
	
	public int getNumServicosCancelados() {
		return numServicosCancelados;
	}
	
	private void setORM_Avaliacoes(java.util.Set value) {
		this.ORM_avaliacoes = value;
	}
	
	private java.util.Set getORM_Avaliacoes() {
		return ORM_avaliacoes;
	}
	
	public final utilizador.Avaliacao_PrestadorSetCollection avaliacoes = new utilizador.Avaliacao_PrestadorSetCollection(this, _ormAdapter, utilizador.ORMConstants.KEY_PRESTADOR_AVALIACOES, utilizador.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
