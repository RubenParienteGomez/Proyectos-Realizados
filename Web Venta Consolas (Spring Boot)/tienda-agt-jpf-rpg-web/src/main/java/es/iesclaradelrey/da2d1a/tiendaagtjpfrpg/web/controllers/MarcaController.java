package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.web.controllers;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.Marca;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.Producto;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.services.MarcaService;

@Controller
@RequestMapping("/marcas")
public class MarcaController {

    private final MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @RequestMapping("/{id}")
    public String detalleMarca(@PathVariable Long id, Model model) {

        // Buscar marca
        Marca marca = marcaService.buscarId(id);

        if (marca == null) {
            return "redirect:/";
        }

        // Obtener productos
        List<Producto> productos = marca.getProductos();

        // Ordenar alfabéticamente
        productos = productos.stream().sorted(Comparator.comparing(Producto::getNombre)).toList();

        model.addAttribute("marca", marca);
        model.addAttribute("productos", productos);

        return "marca-detalle";
    }
}