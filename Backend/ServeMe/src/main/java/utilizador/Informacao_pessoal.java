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

public class Informacao_pessoal {
	public Informacao_pessoal() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == utilizador.ORMConstants.KEY_INFORMACAO_PESSOAL_AVALIACOES) {
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
	
	private Long numTelemovel;
	
	private String morada;
	
	private double classificacao;
	
	private long nif;
	
	private int tipo;
	
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

	public void setNumTelemovel(long value) {
		this.numTelemovel = value;
	}
	
	public Long getNumTelemovel() {
		return numTelemovel;
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
	
	public void setTipo(int value) {
		this.tipo = value;
	}
	
	public int getTipo() {
		return tipo;
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
	
	public final utilizador.AvaliacaoSetCollection avaliacoes = new utilizador.AvaliacaoSetCollection(this, _ormAdapter, utilizador.ORMConstants.KEY_INFORMACAO_PESSOAL_AVALIACOES, utilizador.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public void setNumTelemovel(Long numTelemovel) {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
