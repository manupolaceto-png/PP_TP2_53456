package modelo.actividades;

import java.io.Serializable;

public class Charla extends Actividad implements Serializable {
    private String disertante;

    public Charla(int id, String titulo, int cupoMaximo, String disertante) {
        super(id, titulo, cupoMaximo);
        this.disertante = disertante;
    }

    public double calcularCostoMateriales(){
        return 0 ;
    }

    public String getTipo() {
        return "modelo.actividades.Charla";
    }
}
