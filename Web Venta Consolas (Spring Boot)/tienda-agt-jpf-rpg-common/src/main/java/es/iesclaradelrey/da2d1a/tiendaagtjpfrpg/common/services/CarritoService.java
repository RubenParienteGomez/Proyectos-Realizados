package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.services;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.CarritoItem;
import java.util.List;

public interface CarritoService {
    CarritoItem addProducto(String email, Long productoId, Integer unidades);
    List<CarritoItem> getCarritoByUsuario(String email);
    void eliminarProducto(String email, Long productoId);
    void vaciarCarrito(String email);

    int getNumProductosDistintos(String email);
    int getUnidadesTotales(String email);
    Double getImporteTotal(String email);
}
