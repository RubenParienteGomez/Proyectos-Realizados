package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.web.controllers;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.services.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/categorias")
public class CategoriaAdminController {

    private final CategoriaService categoriaService;

    public CategoriaAdminController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping({"", "/"})
    public String listado(Model model) {
        model.addAttribute("categorias", categoriaService.buscarTodos());
        return "admin/categorias/listado";
    }

    // Mostrar formulario de creación
    @GetMapping("/new")
    public String nueva(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "admin/categorias/formulario";
    }

    // Mostrar formulario de edición
    @GetMapping("/{id}/edit")
    public String editar(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.buscarId(id);
        if (categoria == null) return "redirect:/admin/categorias";
        model.addAttribute("categoria", categoria);
        return "admin/categorias/formulario";
    }

    // Guardar categoría (Crear o Editar)
    @PostMapping("/save")
    public String guardar(@ModelAttribute Categoria categoria, Model model) {
        try {
            categoriaService.guardar(categoria);
            return "redirect:/admin/categorias";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "admin/categorias/formulario";
        }
    }

    // Mostrar confirmación de borrado
    @GetMapping("/{id}/delete")
    public String confirmarEliminar(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.buscarId(id);
        model.addAttribute("categoria", categoria);
        return "admin/categorias/eliminar";
    }

    // Procesar borrado
    @PostMapping("/{id}/delete")
    public String eliminar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            categoriaService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Categoría eliminada con éxito.");
            return "redirect:/admin/categorias";
        } catch (Exception e) {
            model.addAttribute("categoria", categoriaService.buscarId(id));
            model.addAttribute("error", "No se puede eliminar esta categoría porque tiene productos asignados.");

            return "admin/categorias/eliminar";
        }
    }
}