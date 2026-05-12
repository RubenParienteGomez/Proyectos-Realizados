package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.mappers;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.dtos.CategoriaDto;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.Categoria;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    CategoriaDto toDto(Categoria categoria);
    List<CategoriaDto> toDtos(List<Categoria> categorias);
}