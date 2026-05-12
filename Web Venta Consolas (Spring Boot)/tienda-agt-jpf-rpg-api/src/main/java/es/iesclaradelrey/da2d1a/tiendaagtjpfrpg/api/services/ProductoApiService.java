package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.dtos.ProductoDto;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.mappers.ProductoMapper;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.repositories.ProductoRepository;

@Service
public class ProductoApiService {

    private final ProductoRepository repository;
    private final ProductoMapper mapper;

    public ProductoApiService(ProductoRepository repository, ProductoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ProductoDto> getAll() {
        // Todos los productos ordenados por nombre
        return mapper.toDtos(repository.findAll(Sort.by(Sort.Direction.ASC, "nombre")));
    }

    public List<ProductoDto> getByCategoryId(Long id) {
        // Productos por categoría usando consulta derivada + Sort
        return mapper.toDtos(repository.findByCategorias_Id(id, Sort.by(Sort.Direction.ASC, "nombre")));
    }
}