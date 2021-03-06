package EA_ServeMe.responses;

import utilizador.Avaliacao_Cliente;
import utilizador.Avaliacao_Prestador;

import java.io.Serializable;
import java.util.List;

public class PrestadorProfResponse implements Serializable {

    String nome;
    String email;
    long nrTelm;
    String morada;
    String freguesia;
    String concelho;
    String distrito;
    double classificacao;
    int numServicosRealizados;
    int numServicosCancelados;
    List<AvaliacaoResponse> avaliacoes;


    public double getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    public int getNumServicosRealizados() {
        return numServicosRealizados;
    }

    public void setNumServicosRealizados(int numServicosRealizados) {
        this.numServicosRealizados = numServicosRealizados;
    }

    public int getNumServicosCancelados() {
        return numServicosCancelados;
    }

    public void setNumServicosCancelados(int numServicosCancelados) {
        this.numServicosCancelados = numServicosCancelados;
    }



    public PrestadorProfResponse(String nome, String email, long numTelemovel, String morada, String freguesia, String concelho, String distrito, double classificacao, int numServicosRealizados, int numServicosCancelados, List<AvaliacaoResponse> avaliacoes) {
        this.nome = nome;
        this.email = email;
        this.nrTelm = numTelemovel;
        this.morada = morada;
        this.freguesia = freguesia;
        this.concelho = concelho;
        this.distrito = distrito;
        this.classificacao = classificacao;
        this.numServicosRealizados = numServicosRealizados;
        this.numServicosCancelados = numServicosCancelados;
        this.avaliacoes = avaliacoes;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getNrTelm() {
        return nrTelm;
    }

    public void setNrTelm(long nrTelm) {
        this.nrTelm = nrTelm;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getFreguesia() {
        return freguesia;
    }

    public void setFreguesia(String freguesia) {
        this.freguesia = freguesia;
    }

    public String getConcelho() {
        return concelho;
    }

    public void setConcelho(String concelho) {
        this.concelho = concelho;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public List<AvaliacaoResponse> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<AvaliacaoResponse> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
}
