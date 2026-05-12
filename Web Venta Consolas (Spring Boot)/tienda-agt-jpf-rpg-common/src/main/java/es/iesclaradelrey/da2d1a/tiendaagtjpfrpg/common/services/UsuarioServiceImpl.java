package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.Usuario;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.UsuarioRegistroDto;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Usuario buscarId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public void registrar(UsuarioRegistroDto dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setApellidos(dto.getApellidos());
        usuario.setEmail(dto.getEmail());

        // Ciframos la contraseña con el bean de seguridad
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));

        // Fecha automática del sistema
        usuario.setFechaRegistro(LocalDateTime.now());

        usuarioRepository.save(usuario);
    }
}