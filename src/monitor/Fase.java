package monitor;

import java.io.Serializable;

public class Fase implements Serializable {

    private String nombre;
    private int dia;

    public Fase(){
        nombre = "PrimeraFase";
        dia = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }
}
