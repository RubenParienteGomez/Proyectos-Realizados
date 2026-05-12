package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.web.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("")
    public String index(Authentication auth) {
        // Comprobamos si el usuario tiene el rol ADMIN
        boolean esAdmin = auth.getAuthorities().stream()
                            .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if (esAdmin) {
            return "admin/index";
        }
        return "acceso-denegado"; 
    }

    @GetMapping("/")
    public String redirect() {
        return "redirect:/admin";
    }
}
