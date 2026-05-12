package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.web.controllers;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.services.UsuarioService;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.security.components.UsuarioDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users/profile")
public class PerfilController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String verMiPerfil(Authentication auth) {
        UsuarioDetails userDetails = (UsuarioDetails) auth.getPrincipal();
        return "redirect:/users/profile/" + userDetails.getUsuario().getId();
    }

    @GetMapping("/{userId}")
    public String verPerfil(@PathVariable Long userId, Model model, Authentication auth) {
        UsuarioDetails conectado = (UsuarioDetails) auth.getPrincipal();
        
        boolean esAdmin = auth.getAuthorities().stream()
                            .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        boolean esElMismo = conectado.getUsuario().getId().equals(userId);

        if (esAdmin || esElMismo) {
            model.addAttribute("usuario", usuarioService.buscarId(userId));
            return "perfil"; 
        }
        
        return "acceso-denegado"; 
    }
}