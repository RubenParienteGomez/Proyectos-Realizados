package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.dtos.ProductoDto;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.Producto;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    @Mapping(target = "nombreMarca", source = "marca.nombre")
    @Mapping(target = "nombresCategorias", source = "categorias", qualifiedByName = "mapCategorias")
    ProductoDto toDto(Producto producto);

    List<ProductoDto> toDtos(List<Producto> productos);

    @Named("mapCategorias")
    default List<String> mapCategorias(List<Categoria> categorias) {
        if (categorias == null) return null;
        return categorias.stream().map(Categoria::getNombre).collect(Collectors.toList());
    }
}