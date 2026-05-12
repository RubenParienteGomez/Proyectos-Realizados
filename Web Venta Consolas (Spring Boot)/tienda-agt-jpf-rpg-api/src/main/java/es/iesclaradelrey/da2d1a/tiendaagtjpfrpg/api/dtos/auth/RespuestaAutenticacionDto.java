package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.dtos.auth;

public class RespuestaAutenticacionDto {
    private String tokenAcceso;
    private String tokenRefresco;

    public RespuestaAutenticacionDto() {
    }

    public RespuestaAutenticacionDto(String tokenAcceso, String tokenRefresco) {
        this.tokenAcceso = tokenAcceso;
        this.tokenRefresco = tokenRefresco;
    }

    public String getTokenAcceso() {
        return tokenAcceso;
    }

    public void setTokenAcceso(String tokenAcceso) {
        this.tokenAcceso = tokenAcceso;
    }

    public String getTokenRefresco() {
        return tokenRefresco;
    }

    public void setTokenRefresco(String tokenRefresco) {
        this.tokenRefresco = tokenRefresco;
    }
}
