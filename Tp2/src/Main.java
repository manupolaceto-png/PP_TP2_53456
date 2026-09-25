import excepeciones.CupoExcedidoException;
import modelo.Estudiante;
import modelo.EventoUniversitario;
import modelo.Sala;
import modelo.actividades.Actividad;
import modelo.actividades.Charla;
import modelo.actividades.Taller;
import modelo.certificacion.Certificable;

import javax.swing.undo.CannotUndoException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EventoUniversitario evento1 = new EventoUniversitario("EVT-001", "Hackaton", 15000, false);
        Estudiante estudiante1 = new Estudiante("53456", "Juan Manuel Pol");
        Sala sala1 = new Sala(1, "sala1");

        evento1.mostrarDatos();
        evento1.asignarSala(sala1);
        evento1.crearActividad(0 ,"Conferencia de Java",15,"Charla");
        evento1.crearActividad(1 ,"CiberDefensa", 20,"Taller");

        List<Actividad> listaDelEvento = evento1.getActividades();
        Actividad conferncia = listaDelEvento.get(0);
        Actividad taller = listaDelEvento.get(1);

        try{
            conferncia.inscribir(estudiante1);
            taller.inscribir(estudiante1);
            System.out.println("Inscripcion existosa.");
        }
        catch (CupoExcedidoException a){
            System.err.println("Error" + a.getMessage());
        }finally {
            System.out.println("Proceso de inscripcion finalizado.");
        }

        for (Actividad actActual: listaDelEvento){
            System.out.println( "Identificacion de la Actividad: "); actActual.mostrarIdentificacion();
        }

        for (Actividad act: evento1.getActividades()){
            if (act instanceof Certificable certificable){
                String certificado = certificable.generarCertificado(estudiante1);
                System.out.println(certificado);
            }
        }
        System.out.println( "El Costo de $" + evento1.calcularCostoEstimado());
        System.out.println("Cantidad de eventos: " + EventoUniversitario.getCantidadEventos());

        List<Charla> charlaList = evento1.filtrarActividadesPorTipo(Charla.class);
        List<Taller> tallerList = evento1.filtrarActividadesPorTipo(Taller.class);
    }
}