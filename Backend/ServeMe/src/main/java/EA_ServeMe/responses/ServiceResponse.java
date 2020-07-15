package EA_ServeMe.responses;

import servico.Servico;

public class ServiceResponse {

    private int ID;

    private String cliente_nome;
    private String cliente_email;

    private String prestador_nome;
    private String prestador_email;

    private RequestResponse pedido;

    private ProposeProvider proposta;

    private String estado;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public RequestResponse getPedido() {
        return pedido;
    }

    public void setPedido(RequestResponse pedido) {
        this.pedido = pedido;
    }

    public ProposeProvider getProposta() {
        return proposta;
    }

    public void setProposta(ProposeProvider proposta) {
        this.proposta = proposta;
    }

    public ServiceResponse asResponse(Servico s){
        this.ID = s.getID();
        this.cliente_nome = s.getCliente().getNome();
        this.cliente_email = s.getCliente().getEmail();
        this.prestador_nome = s.getPrestador().getNome();
        this.prestador_email = s.getCliente().getEmail();
        this.pedido = new RequestResponse().asResponse(s.getPedido());
        this.proposta = new ProposeProvider().asResponse(s.getProposta());
        this.estado = estadoAsStr(s.getEstado());
        return this;
    }

    private String estadoAsStr(int estado) {
            String res = "";
            switch (estado){
                case -12 :
                    res = "Cancelado";
                    break;
                case -2 :
                    res = "Cancelado";
                    break;
                case -1 :
                    res = "Cancelado";
                    break;
                case 0:
                    res = "Agendado";
                    break;
                case 1:
                    res = "Realizado[Por AvaliarP]";
                    break;
                case 2:
                    res = "Realizado[Por AvaliarC]";
                    break;
                case 12:
                    res = "Finalizado";
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
