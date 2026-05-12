package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.services;

import java.util.List;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.Categoria;

public interface CategoriaService {
    List<Categoria> buscarTodos();
    List<Categoria> buscarVarios(int cantidad);
    Categoria buscarId(Long id);
    Categoria guardar(Categoria categoria);
    void eliminar(Long id);
}
