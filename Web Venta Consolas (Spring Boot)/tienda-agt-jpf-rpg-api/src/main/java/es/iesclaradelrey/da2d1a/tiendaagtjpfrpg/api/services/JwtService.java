package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateAccessToken(UserDetails user);
    String generateRefreshToken(UserDetails user);
    String extractUsername(String token);
    Boolean isTokenExpired(String token);
    Boolean isTokenValid(String token, UserDetails user);
}
