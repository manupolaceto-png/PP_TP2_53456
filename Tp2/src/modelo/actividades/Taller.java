package modelo.actividades;
import java.io.Serializable;

import modelo.Estudiante;
import modelo.certificacion.Certificable;
public class Taller extends Actividad implements Serializable, Certificable {
    private boolean requiereNotebook;

    public Taller  (int id, String titulo, int cupoMaximo, boolean requiereNotebook){
        super(id, titulo, cupoMaximo);
        this.requiereNotebook = requiereNotebook;
    }

    @Override
    public double calcularCostoMateriales(){
        double costo;
        if (requiereNotebook == false){
            costo = 2000;
        }
        else{
            costo = 5000;
        }
        return costo;
    }


    @Override
    public String generarCertificado(Estudiante estudiante) {
        return ("el alumno " + estudiante.getNombre() + " esta certificado para este Taller");
    }

    public String getTipo() {
        return "modelo.actividades.Charla";
    }

}
