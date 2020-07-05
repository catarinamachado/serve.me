package EA_ServeMe.util;

import servico.Pedido;
import servico.Proposta;

public class ProposeResponse {

    private int ID;
    private String prestador_nome;
    private String prestador_email;
    private String categoria;
    private double preco;
    private double precoProposto;
    private String data;
    private String horaInicioDisp;
    private String horaFimDisp;
    private String horaProposta;
    private double duracao;
    private String classe;
    private String descricao;
    //private int estado;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPrestador_nome() {
        return prestador_nome;
    }

    public void setPrestador_nome(String prestador_nome) {
        this.prestador_nome = prestador_nome;
    }

    public String getPrestador_email() {
        return prestador_email;
    }

    public void setPrestador_email(String prestador_email) {
        this.prestador_email = prestador_email;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getPrecoProposto() {
        return precoProposto;
    }

    public void setPrecoProposto(double precoProposto) {
        this.precoProposto = precoProposto;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHoraInicioDisp() {
        return horaInicioDisp;
    }

    public void setHoraInicioDisp(String horaInicioDisp) {
        this.horaInicioDisp = horaInicioDisp;
    }

    public String getHoraFimDisp() {
        return horaFimDisp;
    }

    public void setHoraFimDisp(String horaFimDisp) {
        this.horaFimDisp = horaFimDisp;
    }

    public String getHoraProposta() {
        return horaProposta;
    }

    public void setHoraProposta(String horaProposta) {
        this.horaProposta = horaProposta;
    }

    public double getDuracao() {
        return duracao;
    }

    public void setDuracao(double duracao) {
        this.duracao = duracao;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public ProposeResponse asResponse(Proposta p) {
        this.ID = p.getID();
        this.prestador_nome = p.getPrestador().getNome();
        this.prestador_email = p.getPrestador().getEmail();
        this.classe = p.getPedido().getCategoria().getClasse().getNome();
        this.categoria = p.getPedido().getCategoria().getNome();
        this.preco = p.getPedido().getPrecoHora();
        this.precoProposto = p.getPrecoProposto();
        this.data = DateUtils.asString(p.getPedido().getData(),0);
        this.horaInicioDisp = DateUtils.asString(p.getPedido().getHoraInicioDisp(),1);
        this.horaFimDisp = DateUtils.asString(p.getPedido().getHoraFimDisp(),1);
        this.horaProposta = DateUtils.asString(p.getHoraInicio(),1);
        this.duracao = p.getPedido().getDuracao();
        this.descricao = p.getPedido().getDescricao();
//        this.estado = p.getEstado();
        return this;
    }

}
