package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            nullable = false,
            unique = true,
            length = 100
    )
    private String nombre;

    @Column(length = 2000)
    private String descripcion;

    @Column(length = 500)
    private String imagen;

    // Constructor vacío para jpa
    public Categoria() {}

    public Categoria(Long id, String nombre, String descripcion, String imagen) {
        this.setId(id);
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
        this.setImagen(imagen);
    }

    @ManyToMany(mappedBy = "categorias")
    private List<Producto> productos = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }
}
