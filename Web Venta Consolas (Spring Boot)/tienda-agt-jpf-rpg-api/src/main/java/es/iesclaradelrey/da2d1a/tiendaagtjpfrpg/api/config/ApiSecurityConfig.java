package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // [cite: 111]
public class ApiSecurityConfig {

    @Bean // [cite: 112]
    public SecurityFilterChain apiFilterChain(HttpSecurity http, es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.filters.FiltroAutenticacionJwt jwtAuthFilter) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desactivar CSRF para todo el sitio
            .formLogin(form -> form.disable()) // Desactivar autenticación basada en formularios
            .httpBasic(basic -> basic.disable()) // Desactivar autenticación básica
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Desactivar sesiones
            .logout(logout -> logout.disable()) // Desactivar logout
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v*/auth/**").permitAll() 
                .requestMatchers("/api/v1/xml/**").permitAll() 
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public org.springframework.security.authentication.AuthenticationManager authenticationManager(org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}