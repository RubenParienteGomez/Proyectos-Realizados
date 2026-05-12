package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.dtos.auth;

public class PeticionLoginDto {
    private String usuario;
    private String password;

    public PeticionLoginDto() {
    }

    public PeticionLoginDto(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
