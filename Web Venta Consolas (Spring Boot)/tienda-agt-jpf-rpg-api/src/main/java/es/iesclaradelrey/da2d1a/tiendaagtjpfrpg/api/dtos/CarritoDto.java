package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoDto {
    private List<CarritoItemDetalleDto> items;
    private int numProductosDistintos;
    private int unidadesTotales;
    private Double importeTotal;
}