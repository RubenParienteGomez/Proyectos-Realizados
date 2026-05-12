package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.repositories;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
