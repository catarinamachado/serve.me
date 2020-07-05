package EA_ServeMe.responses;

import java.io.Serializable;

public class MyProfileResponse implements Serializable {

    String nome;
    String email;
    long nrTelm;
    long nif;
    String morada;
    String Freguesia;
    String Concelho;
    String Distrito;


    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }


    public MyProfileResponse(String nome, String email, long nrTelm, long nif, String morada, String freguesia, String concelho, String distrito) {
        this.nome = nome;
        this.email = email;
        this.nrTelm = nrTelm;
        this.nif = nif;
        this.morada=morada;
        Freguesia = freguesia;
        Distrito = distrito;
        Concelho = concelho;
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

    public String getFreguesia() {
        return Freguesia;
    }

    public void setFreguesia(String freguesia) {
        Freguesia = freguesia;
    }

    public String getDistrito() {
        return Distrito;
    }

    public void setDistrito(String distrito) {
        Distrito = distrito;
    }

    public String getConcelho() {
        return Concelho;
    }

    public void setConcelho(String concelho) {
        Concelho = concelho;
    }

    public long getNif() {
        return nif;
    }

    public void setNif(long nif) {
        this.nif = nif;
    }
}
