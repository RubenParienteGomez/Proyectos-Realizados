package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.dtos.auth;

public class RespuestaRefrescoTokenDto {
    private String tokenAcceso;

    public RespuestaRefrescoTokenDto() {
    }

    public RespuestaRefrescoTokenDto(String tokenAcceso) {
        this.tokenAcceso = tokenAcceso;
    }

    public String getTokenAcceso() {
        return tokenAcceso;
    }

    public void setTokenAcceso(String tokenAcceso) {
        this.tokenAcceso = tokenAcceso;
    }
}
