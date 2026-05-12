package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.services.CategoriaService;

@Controller
public class CategoriasController {
    private final CategoriaService categoriaService;

    public CategoriasController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

     @RequestMapping({"/categorias", "/categorias/"})
     public String mostrarCategorias(Model model){
        model.addAttribute("categorias", categoriaService.buscarTodos());
        return "categorias";
     }

     @RequestMapping("/categorias/{id}")
    public String verCategoria(@PathVariable Long id, Model model) {
        model.addAttribute("categoria", categoriaService.buscarId(id));
        return "categoria-detalle";
    }
}
