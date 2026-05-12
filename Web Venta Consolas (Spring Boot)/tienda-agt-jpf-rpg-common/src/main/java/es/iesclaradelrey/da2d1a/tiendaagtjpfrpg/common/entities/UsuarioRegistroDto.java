package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities;

import jakarta.validation.constraints.*;

public class UsuarioRegistroDto {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Formato de email inválido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 4, message = "La contraseña debe tener al menos 4 caracteres")
    private String password;

    @AssertTrue(message = "Debes aceptar los términos y condiciones")
    private boolean aceptoTerminos;

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public boolean isAceptoTerminos() { return aceptoTerminos; }
    public void setAceptoTerminos(boolean aceptoTerminos) { this.aceptoTerminos = aceptoTerminos; }
}
