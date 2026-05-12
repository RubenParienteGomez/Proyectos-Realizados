package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoItemDetalleDto {
    private String nombreProducto;
    private Double precioUnitario;
    private int descuento;
    private Double precioConDescuento;
    private int unidades;
    private Double precioTotalItem;
}