package EA_ServeMe.responses;


import EA_ServeMe.util.DateUtils;
import servico.Proposta;
import servico.PropostaState;

public class ProposeProvider {

    private int ID;
    private double precoProposto;
    private String horaProposta;
    private String vencedora;
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
        this.vencedora = vencedoraasStr(p.getVencedora());
        this.request = new RequestResponse().asResponse(p.getPedido());
        return this;
    }

    private String vencedoraasStr(int vencedora) {
        String res = "";
        switch (vencedora){
            case -2 :
                res = "Rejeitada";
                break;
            case -1 :
                res = "Rejeitada";
                break;
            case 0:
                res = "Pendente";
                break;
            case 1:
                res = "Aceite";
                break;
            case 2:
                res = "Aceite";
                break;

        }
        return res;
    }


    public String getVencedora() {
        return vencedora;
    }

    public void setVencedora(String vencedora) {
        this.vencedora = vencedora;
    }
}
