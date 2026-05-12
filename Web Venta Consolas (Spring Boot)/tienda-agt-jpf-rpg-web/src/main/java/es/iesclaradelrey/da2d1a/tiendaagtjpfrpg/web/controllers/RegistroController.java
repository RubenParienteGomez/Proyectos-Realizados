package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.web.controllers;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.UsuarioRegistroDto;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuarioRegistroDto", new UsuarioRegistroDto());
        return "registro";
    }

    @PostMapping
    public String procesarRegistro(
            @Valid @ModelAttribute("usuarioRegistroDto") UsuarioRegistroDto dto,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "registro";
        }

        try {
            usuarioService.registrar(dto);

            return "redirect:/login?registrado";

        } catch (Exception e) {
            model.addAttribute("error", "No se pudo completar el registro. Es posible que el email ya esté en uso.");
            return "registro";
        }
    }
}