package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.security.entities.SecurityEvento;

@Repository
public interface SecurityEventoRepository extends JpaRepository<SecurityEvento, Long> {
}