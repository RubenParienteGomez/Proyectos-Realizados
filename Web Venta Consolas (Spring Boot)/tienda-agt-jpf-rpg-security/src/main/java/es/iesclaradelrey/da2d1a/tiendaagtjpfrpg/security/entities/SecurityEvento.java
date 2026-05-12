package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.security.entities;

import java.time.Instant;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.security.enums.SecurityTipoEvento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity@Table(name = "eventos_seguridad")
public class SecurityEvento {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tiempo_evento", nullable = false)
    private Instant tiempoEvento;

    @Column(name = "usuario", nullable = false, length =150)
    private String usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_evento", nullable = false, length =30)
    private SecurityTipoEvento tipoEvento;

    @Column(name = "ip_address", length =45)
    private String ipAddress;

    public SecurityEvento() {}

    public SecurityEvento(Instant tiempoEvento, String usuario, SecurityTipoEvento tipoEvento, String ipAddress) {
        this.tiempoEvento = tiempoEvento;
        this.usuario = usuario;
        this.tipoEvento = tipoEvento;
        this.ipAddress = ipAddress;
    }

    public Long getId() {
        return id;
    }

    public Instant getTiempoEvento() {
        return tiempoEvento;
    }

    public void setTiempoEvento(Instant tiempoEvento) {
        this.tiempoEvento = tiempoEvento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public SecurityTipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(SecurityTipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}