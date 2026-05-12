package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.services;

import java.util.List;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.Marca;

public interface MarcaService {
    List<Marca> buscarTodos();
    Marca buscarId(Long id);
    Marca guardar(Marca marca);
    void eliminar(Long id);
}