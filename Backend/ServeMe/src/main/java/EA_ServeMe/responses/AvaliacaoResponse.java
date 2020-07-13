package EA_ServeMe.responses;

import java.io.Serializable;

public class AvaliacaoResponse implements Serializable {

    private String avaliador;
    private double classificacao;
    private String opiniao;

    public String getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(String avaliador) {
        this.avaliador = avaliador;
    }

    public double getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    public String getOpiniao() {
        return opiniao;
    }

    public AvaliacaoResponse() {
    }

    public AvaliacaoResponse(String avaliador, double classificacao, String opiniao) {
        this.avaliador = avaliador;
        this.classificacao = classificacao;
        this.opiniao = opiniao;
    }

    public void setOpiniao(String opiniao) {
        this.opiniao = opiniao;
    }
}
