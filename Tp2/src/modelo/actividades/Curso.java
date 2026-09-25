package modelo.actividades;
import modelo.Estudiante;
import modelo.certificacion.Certificable;
public class Curso extends Actividad implements Certificable{
    private int nivel;

    public Curso (int Id, String titulo, int CupoMaximo, int nivel){
        super (Id, titulo, CupoMaximo);
        this.nivel = nivel;
    }
    @Override
    public double calcularCostoMateriales(){
        return 0;
    }
    public String getTipo(){
        return "modelo.actividades.Curso";
    }

    @Override
    public String generarCertificado(Estudiante estudiante) {
        return ("El alumno " + estudiante.getNombre() + " esta certificado para este Curso" );
    }
}
