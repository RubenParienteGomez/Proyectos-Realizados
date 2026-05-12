package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.dtos.CategoriaDto;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.mappers.CategoriaMapper;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.repositories.CategoriaRepository;

@Service
public class CategoriaApiService {

    private final CategoriaRepository repository;
    private final CategoriaMapper mapper;

    public CategoriaApiService(CategoriaRepository repository, CategoriaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<CategoriaDto> getAllOrdered() {
        // Uso de Sort
        return mapper.toDtos(repository.findAll(Sort.by(Sort.Direction.ASC, "nombre")));
    }
}