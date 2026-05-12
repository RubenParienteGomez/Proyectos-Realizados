package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities;

import lombok.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoItemId implements Serializable {
    private Long usuario;
    private Long producto;
}
