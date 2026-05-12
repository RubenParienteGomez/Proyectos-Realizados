package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(
        nullable = false,
        length = 13,
        unique = true
    )
    private String codigo;

    @Column(
        nullable = false,
        length = 200
    )
    private String nombre;

    @ManyToOne(optional = false)
    @JoinColumn(name = "marca_id", nullable = false)
    private Marca marca;
    
    @Column(
        nullable = false,
        length = 4000
    )
    private String descripcion;

    @Column(
        length = 500
    )
    private  String imagen;

    @Column(
        nullable = false
    )
    private Double precio;

    @Column(
        nullable = false
    )
    private int descuento;

    @Column(
            nullable = false
    )
    private int stock;

    public Producto() {}

    public Producto(Long id, String codigo, String nombre, Marca marca, String descripcion, String imagen, Double precio, int descuento) {
        this.setId(id);
        this.setCodigo(codigo);
        this.setNombre(nombre);
        this.setMarca(marca);
        this.setDescripcion(descripcion);
        this.setImagen(imagen);      
        this.setPrecio(precio);
        this.setDescuento(descuento);
    }

    @ManyToMany
    @JoinTable(
        name = "producto_categoria",
        joinColumns = @JoinColumn(name = "producto_id"),
        inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<Categoria> categorias = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getCodigo() { return codigo; }

    public void setDescuento(int descuento) { this.descuento = descuento;}
    public int getDescuento() { return descuento; }

    public void setMarca(Marca marca) { this.marca = marca; }
    public Marca getMarca() { return marca; }
    
    public void setPrecio(Double precio) { this.precio = precio; }
    public Double getPrecio() { return precio; }

    public List<Categoria> getCategorias() { return categorias; }
    public void setCategorias(List<Categoria> categorias) { this.categorias = categorias;}

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}