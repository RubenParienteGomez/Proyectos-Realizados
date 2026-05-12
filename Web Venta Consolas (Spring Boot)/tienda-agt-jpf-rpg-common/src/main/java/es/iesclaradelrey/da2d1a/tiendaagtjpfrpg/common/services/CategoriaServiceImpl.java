package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.services;

import java.util.List;

import org.springframework.stereotype.Service;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.repositories.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> buscarTodos() {
        return categoriaRepository.findAll();
    }

    @Override
    public List<Categoria> buscarVarios(int cantidad) {
        return categoriaRepository.findAll()
                .stream()
                .limit(cantidad)
                .toList();
    }

    @Override
    public Categoria buscarId(Long id) {
        return categoriaRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Categoria guardar(Categoria categoria) {

        if (categoria.getNombre() == null || categoria.getNombre().isBlank()) {
            throw new RuntimeException("El nombre de la categoria es obligatorio");
        }

        if (categoria.getDescripcion() == null || categoria.getDescripcion().isBlank()) {
            throw new RuntimeException("La descripción de la categoria es obligatorio");
        }

        Categoria existente = categoriaRepository.findByNombre(categoria.getNombre());

        if (existente != null) {
            if (categoria.getId() == null || !existente.getId().equals(categoria.getId())) {
                throw new RuntimeException("Ya existe una categoría con ese nombre");
            }
        }

        return categoriaRepository.save(categoria);
    }

    @Override
    public void eliminar(Long id) {
        categoriaRepository.deleteById(id);
    }
}