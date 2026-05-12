package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.dtos.CategoriaDto;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.services.CategoriaApiService;

@RestController
@RequestMapping("/api/v1")
public class CategoriaRestController {

    private final CategoriaApiService categoriaService;

    public CategoriaRestController(CategoriaApiService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/categories")
    public List<CategoriaDto> getAll() {
        return categoriaService.getAllOrdered();
    }
}