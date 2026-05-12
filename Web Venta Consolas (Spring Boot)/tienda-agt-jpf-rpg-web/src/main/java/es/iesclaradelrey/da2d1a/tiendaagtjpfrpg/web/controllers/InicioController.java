package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.web.controllers;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.services.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class  InicioController {
    private final CategoriaService categoriaService;

    public InicioController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // Recoge las peticiones que terminen o no en "/" y carga el index.html
    @GetMapping({"", "/"})
    public String mostrarInicio(Model model){
        model.addAttribute("categorias", categoriaService.buscarVarios(3));
        return "index";
    }
}
