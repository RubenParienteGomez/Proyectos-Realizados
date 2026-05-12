package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.security.services;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.Usuario;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.repositories.UsuarioRepository;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.security.components.UsuarioDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AutenticacionService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new UsuarioDetails(usuario);
    }


}

