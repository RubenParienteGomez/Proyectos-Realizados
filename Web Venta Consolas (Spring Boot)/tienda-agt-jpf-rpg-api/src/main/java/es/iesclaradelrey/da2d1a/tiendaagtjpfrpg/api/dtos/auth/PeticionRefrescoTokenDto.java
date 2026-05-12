package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.dtos.auth;

public class PeticionRefrescoTokenDto {
    private String tokenRefresco;

    public PeticionRefrescoTokenDto() {
    }

    public PeticionRefrescoTokenDto(String tokenRefresco) {
        this.tokenRefresco = tokenRefresco;
    }

    public String getTokenRefresco() {
        return tokenRefresco;
    }

    public void setTokenRefresco(String tokenRefresco) {
        this.tokenRefresco = tokenRefresco;
    }
}
