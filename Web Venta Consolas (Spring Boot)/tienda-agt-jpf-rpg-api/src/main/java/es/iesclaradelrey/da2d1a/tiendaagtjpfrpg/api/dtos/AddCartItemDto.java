package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.dtos;

import lombok.Data;

@Data
public class AddCartItemDto {
    private Long productoId;
    private Integer unidades;
}