package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.dtos;

public class CategoriaDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private String imagen;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }
}