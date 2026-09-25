package excepeciones;

public class CupoExcedidoException extends Exception {
    public CupoExcedidoException (String nombreError){
        super (nombreError);
    }
}
