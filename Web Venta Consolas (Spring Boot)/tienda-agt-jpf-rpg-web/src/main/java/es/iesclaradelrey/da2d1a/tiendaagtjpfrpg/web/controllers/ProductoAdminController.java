package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.web.controllers;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.Producto;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.services.CategoriaService;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.services.MarcaService;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.services.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/productos")
public class ProductoAdminController {

    private final ProductoService productoService;
    private final MarcaService marcaService;
    private final CategoriaService categoriaService;

    public ProductoAdminController(ProductoService productoService, MarcaService marcaService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.marcaService = marcaService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("")
    public String listado(Model model) {
        model.addAttribute("productos", productoService.buscarTodos());
        return "admin/productos/listado";
    }

    // Crear
    @GetMapping("/new")
    public String nuevoProducto(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("marcas", marcaService.buscarTodos());
        model.addAttribute("todasCategorias", categoriaService.buscarTodos());
        return "admin/productos/formulario";
    }

    // Editar
    @GetMapping("/{id}/edit")
    public String editarProducto(@PathVariable Long id, Model model) {
        Producto producto = productoService.buscarId(id);
        if (producto == null) return "redirect:/admin/productos";

        model.addAttribute("producto", producto);
        model.addAttribute("marcas", marcaService.buscarTodos());
        model.addAttribute("todasCategorias", categoriaService.buscarTodos());
        return "admin/productos/formulario";
    }

    // Guardar
    @PostMapping("/save")
    public String guardar(@ModelAttribute Producto producto, Model model) {
        try {
            // Comprobar que se selecciona una marca
            if (producto.getMarca() == null || producto.getMarca().getId() == null) {
                throw new RuntimeException("Debes seleccionar una marca para el producto.");
            }

            // Comprobar que se selecciona una categoría
            if (producto.getCategorias() == null || producto.getCategorias().isEmpty()) {
                throw new RuntimeException("El producto debe pertenecer al menos a una categoría.");
            }

            productoService.guardar(producto);
            return "redirect:/admin/productos";
        } catch (Exception e) {
            // Si hay error recargamos los datos necesarios
            model.addAttribute("error", e.getMessage());
            model.addAttribute("marcas", marcaService.buscarTodos());
            model.addAttribute("todasCategorias", categoriaService.buscarTodos());
            return "admin/productos/formulario";
        }
    }

    // Eliminar
    @GetMapping("/{id}/delete")
    public String confirmarEliminar(@PathVariable Long id, Model model) {
        Producto producto = productoService.buscarId(id);
        model.addAttribute("producto", producto);
        return "admin/productos/eliminar";
    }

    @PostMapping("/{id}/delete")
    public String eliminar(@PathVariable Long id, Model model) {
        try {
            productoService.eliminar(id);
            return "redirect:/admin/productos";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("producto", productoService.buscarId(id));
            return "admin/productos/eliminar";
        }
    }
}