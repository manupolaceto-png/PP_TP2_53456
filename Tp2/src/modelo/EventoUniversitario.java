package modelo;

import modelo.actividades.Actividad;
import modelo.actividades.Charla;
import modelo.actividades.Taller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

    public class EventoUniversitario implements Serializable {
    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos;
    private Sala sala;
    private List<Actividad> actividades;

    public EventoUniversitario( String id, String titulo, double costoBase, boolean gratuito) {
        this.id = id;
        this.titulo = titulo;
        this.costoBase = costoBase;
        this.gratuito = gratuito;
        ++cantidadEventos;
        this.actividades = new ArrayList<>();
    }
    public EventoUniversitario (EventoUniversitario otro){
        this.id = otro.id;
        this.titulo = otro.titulo;
        this.costoBase = otro.costoBase;
        this.gratuito = otro.gratuito;
        ++cantidadEventos;
        this.actividades = new ArrayList<>();
    }


    public void mostrarDatos() {
        System.out.println("ID: " + this.id + " - Título: " + this.titulo + " - Costo: " + this.costoBase + " - Gratuito: " + this.gratuito );
    }

    public static int getCantidadEventos() {
        return cantidadEventos;
    }


    public void crearActividad(int id, String titulo, int cupo, String tipo) {
        if (tipo.equals("Charla")){
            this.actividades.add(new Charla(id,titulo,cupo,"Sin definir"));
        }
        else{
            this.actividades.add(new Taller(id,titulo,cupo,true));
        }
    }

    public void asignarSala(Sala sala) {
        this.sala = sala;
        System.out.println("El evento fua asignado a una sala");
    }

    public List<Actividad> getActividades  (){
        return this.actividades;
    }

    public double calcularCostoEstimado() {
        if (this.gratuito) {
                return 0;
        } else {
                double costoMateriales = calcularCostoMateriales(this.actividades);
                double costoTotal = this.costoBase + costoMateriales;
                return costoTotal * 1.21;
        }
    }
    public double calcularCostoMateriales(List<? extends Actividad> actividades) {
        double total = 0;
        for (Actividad act : actividades) {
            total += act.calcularCostoMateriales();
        }
        return total;
    }

    public void persistir(){
        String nombreArchivo = "archivo.dat";
        try {
            FileOutputStream fos = new FileOutputStream(nombreArchivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
        }catch (FileNotFoundException e){
            System.err.println("Error: No se pudo crear el archivo " + nombreArchivo);
        }catch (IOException a){
            System.err.println("Error de E/S al escribir el archivo" + nombreArchivo);
        }
    }
    public static EventoUniversitario recuperarEvento(String nombreArchivo){
        EventoUniversitario evento = null;
        try {
            FileInputStream fis = new FileInputStream(nombreArchivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            evento = (EventoUniversitario) ois.readObject();
            ois.close();
            fis.close();
        } catch (FileNotFoundException e){
            System.err.println("Error: No se pudo leer el archivo " + nombreArchivo);
        } catch (IOException e){
            System.err.println("Error de E/S al leer el archivo " + nombreArchivo);
        } catch (ClassNotFoundException e){
            System.err.println("Error: Archivo en clase no encontrada " + nombreArchivo);
        }
        return evento;
    }

    public <T extends  Actividad> List<T> filtrarActividadesPorTipo(Class <T> tipo){
        List<T> resultado = new ArrayList<>();

        for (Actividad act : this.actividades){
            if (tipo.isInstance(act)){
                resultado.add((T) act);
            }
        }
        return resultado;
    }

}
