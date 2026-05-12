package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "carrito_items")
@IdClass(CarritoItemId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoItem {
    @Id
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Id
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    private Integer unidades;
    private LocalDateTime fechaActualizacion;
}