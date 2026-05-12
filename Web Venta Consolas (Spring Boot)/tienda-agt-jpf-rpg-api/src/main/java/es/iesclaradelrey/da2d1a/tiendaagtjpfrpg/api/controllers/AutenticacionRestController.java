package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.controllers;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.dtos.auth.RespuestaAutenticacionDto;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.dtos.auth.PeticionLoginDto;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.dtos.auth.PeticionRefrescoTokenDto;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.dtos.auth.RespuestaRefrescoTokenDto;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.services.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AutenticacionRestController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public AutenticacionRestController(AuthenticationManager authenticationManager, JwtService jwtService, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping({"", "/"})
    public ResponseEntity<RespuestaAutenticacionDto> login(@RequestBody PeticionLoginDto peticion) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(peticion.getUsuario(), peticion.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String tokenAcceso = jwtService.generateAccessToken(userDetails);
            String tokenRefresco = jwtService.generateRefreshToken(userDetails);

            return ResponseEntity.ok(new RespuestaAutenticacionDto(tokenAcceso, tokenRefresco));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<RespuestaRefrescoTokenDto> refresh(@RequestBody PeticionRefrescoTokenDto peticion) {
        try {
            String tokenRefresco = peticion.getTokenRefresco();
            String nombreUsuario = jwtService.extractUsername(tokenRefresco);

            if (nombreUsuario != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(nombreUsuario);

                if (jwtService.isTokenValid(tokenRefresco, userDetails)) {
                    String tokenAcceso = jwtService.generateAccessToken(userDetails);
                    return ResponseEntity.ok(new RespuestaRefrescoTokenDto(tokenAcceso));
                }
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
