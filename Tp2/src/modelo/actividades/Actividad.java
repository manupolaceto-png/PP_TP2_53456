package modelo.actividades;

import excepeciones.CupoExcedidoException;
import modelo.Estudiante;
import modelo.Inscripcion;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Actividad implements Serializable {
    private int id;
    private String titulo;
    private int cupoMaximo;
    public final int CUPO_MINIMO = 0;
    private List<Inscripcion> inscripciones;

    public Inscripcion inscribir(Estudiante estudiante)throws CupoExcedidoException {
        if (inscripciones.size()>= this.cupoMaximo){
            throw new CupoExcedidoException("Cupo maximo alcancado.");
        }else {
            Inscripcion nuevaInscripcion = new Inscripcion(LocalDate.now(), "activa");
            this.inscripciones.add(nuevaInscripcion);
            return nuevaInscripcion;
        }

    }
    public Actividad (int id, String titulo, int cupoMaximo){
        this.id = id;
        this.titulo = titulo;
        this.cupoMaximo = cupoMaximo;
        this.inscripciones = new ArrayList<>();
    }

    public abstract String getTipo();
    public abstract double calcularCostoMateriales();
    public final void mostrarIdentificacion(){
        System.out.println("ID:" + this.id + " Titulo:" + this.titulo + " Tipo:" + this.getTipo());
    }

}
