package EA_ServeMe.responses;


import java.io.Serializable;

public class AuthResponse implements Serializable {


    private String token;
    private String nome;
    private int status;


    public AuthResponse(String token, String nome) {
        this.token = token;
        this.nome = nome;
    }

    public AuthResponse() {
        nome = "";
        token="";
        status = -1;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public AuthResponse error(String err){
        this.nome = "error";
        this.token = err;
        this.status = -1;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
