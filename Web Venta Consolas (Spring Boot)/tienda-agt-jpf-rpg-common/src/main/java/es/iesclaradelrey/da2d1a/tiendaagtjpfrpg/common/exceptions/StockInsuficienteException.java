package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.exceptions;

public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
