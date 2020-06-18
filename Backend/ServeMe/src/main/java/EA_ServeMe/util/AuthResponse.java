package EA_ServeMe.util;


import lombok.Data;

import java.io.Serializable;

public class AuthResponse implements Serializable {


    private String token;
    private String nome;


    public AuthResponse(String token, String nome) {
        this.token = token;
        this.nome = nome;
    }

    public AuthResponse() {
        nome = "";
        token="";
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
        return this;
    }
}
