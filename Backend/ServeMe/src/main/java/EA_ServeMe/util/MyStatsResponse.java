package EA_ServeMe.util;

import utilizador.Avaliacao_Prestador;

import java.util.*;
import java.awt.geom.Point2D;
import java.io.Serializable;

public class MyStatsResponse implements Serializable {

     int ano;
     double ganho_anual;
     int servicos_anual;
     List<Dot> ganhos_por_mes;
     List<Dot> servicos_por_mes;
     List<Dot> servicos_por_subcat;


    public MyStatsResponse() {
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getGanho_anual() {
        return ganho_anual;
    }

    public void setGanho_anual(double ganho_anual) {
        this.ganho_anual = ganho_anual;
    }

    public int getServicos_anual() {
        return servicos_anual;
    }

    public void setServicos_anual(int servicos_anual) {
        this.servicos_anual = servicos_anual;
    }

    public List<Dot> getGanhos_por_mes() {
        return ganhos_por_mes;
    }

    public void setGanhos_por_mes(List<Dot> ganhos_por_mes) {
        this.ganhos_por_mes = ganhos_por_mes;
    }

    public List<Dot> getServicos_por_mes() {
        return servicos_por_mes;
    }

    public void setServicos_por_mes(List<Dot> servicos_por_mes) {
        this.servicos_por_mes = servicos_por_mes;
    }

    public List<Dot> getServicos_por_subcat() {
        return servicos_por_subcat;
    }

    public void setServicos_por_subcat(List<Dot> servicos_por_subcat) {
        this.servicos_por_subcat = servicos_por_subcat;
    }
}
