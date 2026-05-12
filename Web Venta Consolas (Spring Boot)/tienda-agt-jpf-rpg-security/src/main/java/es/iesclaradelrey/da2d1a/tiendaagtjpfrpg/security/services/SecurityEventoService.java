package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.security.services;

import java.time.Instant;

import org.springframework.stereotype.Service;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.security.entities.SecurityEvento;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.security.enums.SecurityTipoEvento;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.security.repositories.SecurityEventoRepository;

@Service
public class SecurityEventoService {

    private final SecurityEventoRepository repository;

    public SecurityEventoService(SecurityEventoRepository repository) {
        this.repository = repository;
    }

    public SecurityEvento record(String username, SecurityTipoEvento type, String ipAddress) {
        SecurityEvento event = new SecurityEvento(Instant.now(), username, type, ipAddress);
        return repository.save(event);
    }
}