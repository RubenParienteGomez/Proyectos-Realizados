package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.services;

import java.util.Optional;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.Usuario; // Importante para el DTO
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.UsuarioRegistroDto;

public interface UsuarioService {
    Usuario guardar(Usuario usuario);
    Optional<Usuario> buscarEmail(String email);
    Usuario buscarId(Long id);
    void registrar(UsuarioRegistroDto usuarioDto);
}