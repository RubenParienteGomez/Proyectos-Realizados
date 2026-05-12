package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.controllers;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.dtos.*;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.CarritoItem;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.services.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cart")
public class CartRestController {

    @Autowired
    private CarritoService carritoService;

    @PostMapping
    public ResponseEntity<CarritoDto> add(@RequestBody AddCartItemDto dto, Principal principal) {
        carritoService.addProducto(principal.getName(), dto.getProductoId(), dto.getUnidades());
        return ResponseEntity.ok(getCarritoCompleto(principal));
    }

    @GetMapping
    public ResponseEntity<CarritoDto> get(Principal principal) {
        return ResponseEntity.ok(getCarritoCompleto(principal));
    }

    // Eliminar producto específico
    @DeleteMapping("/{productid}")
    public ResponseEntity<CarritoDto> delete(@PathVariable Long productid, Principal principal) {
        carritoService.eliminarProducto(principal.getName(), productid);
        return ResponseEntity.ok(getCarritoCompleto(principal)); // Devuelve el estado actual del carro
    }

    // Vaciar todo el carro
    @DeleteMapping
    public ResponseEntity<CarritoDto> clear(Principal principal) {
        carritoService.vaciarCarrito(principal.getName());
        return ResponseEntity.ok(getCarritoCompleto(principal));
    }

    private CarritoDto getCarritoCompleto(Principal principal) {
        String email = principal.getName();
        List<CarritoItem> itemsEntidad = carritoService.getCarritoByUsuario(email);

        System.out.println("CONSOLA: Buscando carrito para email: " + email);
        System.out.println("CONSOLA: Cantidad de items encontrados: " + itemsEntidad.size());

        if (itemsEntidad.isEmpty()) {
            System.out.println("CONSOLA: ¡El carrito está vacío en la BD!");
        }

        List<CarritoItemDetalleDto> detalles = itemsEntidad.stream().map(item -> {
            CarritoItemDetalleDto detalle = new CarritoItemDetalleDto();
            double precioOriginal = item.getProducto().getPrecio();
            int descuento = item.getProducto().getDescuento();
            double precioConDescuento = precioOriginal * (1 - (descuento / 100.0));
            precioConDescuento = Math.round(precioConDescuento * 100.0) / 100.0;

            detalle.setNombreProducto(item.getProducto().getNombre());
            detalle.setPrecioUnitario(precioOriginal);
            detalle.setDescuento(descuento);
            detalle.setPrecioConDescuento(precioConDescuento);
            detalle.setUnidades(item.getUnidades());
            detalle.setPrecioTotalItem(precioConDescuento * item.getUnidades());
            return detalle;
        }).collect(Collectors.toList());

        CarritoDto carritoDto = new CarritoDto();
        carritoDto.setItems(detalles);
        carritoDto.setNumProductosDistintos(detalles.size());
        carritoDto.setUnidadesTotales(detalles.stream().mapToInt(CarritoItemDetalleDto::getUnidades).sum());
        carritoDto.setImporteTotal(detalles.stream().mapToDouble(CarritoItemDetalleDto::getPrecioTotalItem).sum());

        return carritoDto;
    }


}