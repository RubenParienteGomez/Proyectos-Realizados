package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.dtos.ProductoDto;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.services.ProductoApiService;

@RestController
@RequestMapping("/api/v1")
public class ProductoRestController {

    private final ProductoApiService service;

    public ProductoRestController(ProductoApiService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public List<ProductoDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/categories/{id}/products")
    public List<ProductoDto> getByCategoria(@PathVariable Long id) {
        return service.getByCategoryId(id);
    }
}