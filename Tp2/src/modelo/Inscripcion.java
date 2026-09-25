package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Inscripcion implements Serializable {
    private LocalDate fecha;
    private String estado;

    public Inscripcion (LocalDate fecha, String estado){
        this.fecha = fecha;
        this.estado = estado;
    }
}
