package EA_ServeMe.util;

import java.io.Serializable;

public class Dot implements Serializable {

    String eixo_x;
    double eixo_y;


    public String getEixo_x() {
        return eixo_x;
    }

    public void setEixo_x(String eixo_x) {
        this.eixo_x = eixo_x;
    }

    public double getEixo_y() {
        return eixo_y;
    }

    public Dot(String eixo_x, double eixo_y) {
        this.eixo_x = eixo_x;
        this.eixo_y = eixo_y;
    }

    public void setEixo_y(double eixo_y) {
        this.eixo_y = eixo_y;
    }

    public Dot() {
    }


    public void incY(double y){
        this.eixo_y+=y;
    }

}
