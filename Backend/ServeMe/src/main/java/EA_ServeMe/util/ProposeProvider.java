package EA_ServeMe.util;


import servico.Proposta;

public class ProposeProvider {

    private int ID;
    private double precoProposto;
    private String horaProposta;
    private int vencedora;
    private RequestResponse request;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getPrecoProposto() {
        return precoProposto;
    }

    public void setPrecoProposto(double precoProposto) {
        this.precoProposto = precoProposto;
    }

    public String getHoraProposta() {
        return horaProposta;
    }

    public void setHoraProposta(String horaProposta) {
        this.horaProposta = horaProposta;
    }

    public int getVencedora() {
        return vencedora;
    }

    public void setVencedora(int vencedora) {
        this.vencedora = vencedora;
    }

    public RequestResponse getRequest() {
        return request;
    }

    public void setRequest(RequestResponse request) {
        this.request = request;
    }

    public ProposeProvider asResponse(Proposta p){
        this.ID = p.getID();
        this.horaProposta = DateUtils.asString(p.getHoraInicio(),1);
        this.precoProposto = p.getPrecoProposto();
        this.vencedora = p.getVencedora();
        this.request = new RequestResponse().asResponse(p.getPedido());
        return this;
    }


}
