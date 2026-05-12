package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
    Optional<Marca> findByNombre(String nombre);
    List<Marca> findAllByOrderByIdAsc();
}