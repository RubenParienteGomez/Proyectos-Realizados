package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.web.controllers;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.Marca;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.services.MarcaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/marcas")
public class MarcaAdminController {

    private final MarcaService marcaService;

    public MarcaAdminController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping({"", "/"})
    public String listado(Model model) {
        model.addAttribute("marcas", marcaService.buscarTodos());
        return "admin/marcas/listado";
    }

    // Mostrar formulario de creacion
    @GetMapping("/new")
    public String nueva(Model model) {
        model.addAttribute("marca", new Marca());
        return "admin/marcas/formulario";
    }

    // Mostrar formulario de edicion
    @GetMapping("/{id}/edit")
    public String editar(@PathVariable Long id, Model model) {
        Marca marca = marcaService.buscarId(id);
        if (marca == null) return "redirect:/admin/marcas";
        model.addAttribute("marca", marca);
        return "admin/marcas/formulario";
    }

    // Guardar marca (Crear o Editar)
    @PostMapping("/save")
    public String guardar(@ModelAttribute Marca marca, Model model) {
        try {
            marcaService.guardar(marca);
            return "redirect:/admin/marcas";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "admin/marcas/formulario";
        }
    }

    // Mostrar confirmación de borrado
    @GetMapping("/{id}/delete")
    public String confirmarEliminar(@PathVariable Long id, Model model) {
        Marca marca = marcaService.buscarId(id);
        model.addAttribute("marca", marca);
        return "admin/marcas/eliminar";
    }

    // Procesar borrado
    @PostMapping("/{id}/delete")
    public String eliminar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            marcaService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Marca eliminada correctamente.");
            return "redirect:/admin/marcas";
        } catch (Exception e) {
            model.addAttribute("marca", marcaService.buscarId(id));
            model.addAttribute("error", "No se puede eliminar esta marca porque tiene productos asociados.");

            // Asegúrate de que el nombre coincide con tu archivo HTML (eliminar o delete)
            return "admin/marcas/eliminar";
        }
    }
}
