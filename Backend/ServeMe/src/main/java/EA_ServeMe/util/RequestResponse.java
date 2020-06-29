package EA_ServeMe.util;

import servico.Pedido;

public class RequestResponse {
    private int ID;
    private String cliente_nome;
    private String cliente_email;
    private String categoria;
    private double preco;
    private String data;
    private String horaInicioDisp;
    private String horaFimDisp;
    private double duracao;
    private String classe;
    private String descricao;
    private int estado;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getCliente_nome() {
        return cliente_nome;
    }

    public void setCliente_nome(String cliente_nome) {
        this.cliente_nome = cliente_nome;
    }

    public String getCliente_email() {
        return cliente_email;
    }

    public void setCliente_email(String cliente_email) {
        this.cliente_email = cliente_email;
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

    public double getDuracao() {
        return duracao;
    }

    public void setDuracao(double duracao) {
        this.duracao = duracao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public RequestResponse asResponse(Pedido p) {
        this.ID = p.getID();
        this.cliente_email = p.getCliente().getEmail();
        this.cliente_nome = p.getCliente().getNome();
        this.classe = p.getCategoria().getClasse().getNome();
        this.categoria = p.getCategoria().getNome();
        this.preco = p.getPrecoHora();
        this.data = DateUtils.asString(p.getData(),0);
        this.horaInicioDisp = DateUtils.asString(p.getHoraInicioDisp(),1);
        this.horaFimDisp = DateUtils.asString(p.getHoraFimDisp(),1);
        this.duracao = p.getDuracao();
        this.descricao = p.getDescricao();
        this.estado = p.getEstado();
        return this;
    }
}
