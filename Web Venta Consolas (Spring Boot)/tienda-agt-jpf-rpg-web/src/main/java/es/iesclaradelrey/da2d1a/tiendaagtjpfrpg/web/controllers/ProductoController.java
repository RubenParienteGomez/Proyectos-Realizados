package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.web.controllers;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.Producto;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.services.ProductoService;

@Controller
public class ProductoController {
    private final ProductoService productoService;
   
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @RequestMapping("/productos")
    public String verProducto(Model model) {
        model.addAttribute("productos", productoService.buscarTodos());
        return "productos";
    }

    @RequestMapping("/productos/{id}")
    public String verProducto(@PathVariable Long id, Model model) {
        
        Producto producto = productoService.buscarId(id);

        List<Categoria> categorias = producto.getCategorias()
            .stream()
            .sorted(Comparator.comparing(Categoria::getNombre))
            .toList();

        model.addAttribute("categorias", categorias);
        model.addAttribute("producto", productoService.buscarId(id));
        
        return "producto-detalle";
    }
}