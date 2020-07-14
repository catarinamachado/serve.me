package EA_ServeMe.responses;

import EA_ServeMe.util.DateUtils;
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
    private String estado;
    private String morada;
    private String distrito;
    private String concelho;
    private String freguesia;

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getConcelho() {
        return concelho;
    }

    public void setConcelho(String concelho) {
        this.concelho = concelho;
    }

    public String getFreguesia() {
        return freguesia;
    }

    public void setFreguesia(String freguesia) {
        this.freguesia = freguesia;
    }

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
        this.data = DateUtils.asString(p.getData(), 0);
        this.horaInicioDisp = DateUtils.asString(p.getHoraInicioDisp(), 1);
        this.horaFimDisp = DateUtils.asString(p.getHoraFimDisp(), 1);
        this.duracao = p.getDuracao();
        this.descricao = p.getDescricao();
        this.estado = estadoAsStr(p.getEstado());
        this.morada = p.getCliente().getMorada();
        this.distrito = p.getCliente().getDistrito();
        this.concelho = p.getCliente().getConcelho();
        this.freguesia = p.getCliente().getFreguesia();
        return this;
    }

    private String estadoAsStr(int estado) {
        String res = "";
        switch (estado) {
            case -1:
                res = "Em Espera";
                break;
            case 0:
                res = "Em Analise";
                break;
            case 1:
                res = "Servico";
                break;

        }
        return res;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
