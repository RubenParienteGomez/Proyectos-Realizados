package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.Categoria;

// Se crea el repositorio especifico
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    boolean existsByNombre(String nombre);
    Categoria findByNombre(String nombre);
}
