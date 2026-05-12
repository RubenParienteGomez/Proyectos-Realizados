package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.services;

import java.util.List;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.Producto;

public interface ProductoService {
    List<Producto> buscarTodos();
    List<Producto> buscarVarios(int cantidad);
    Producto buscarId(Long id);
    Producto guardar(Producto producto);
    void eliminar(Long id);
}