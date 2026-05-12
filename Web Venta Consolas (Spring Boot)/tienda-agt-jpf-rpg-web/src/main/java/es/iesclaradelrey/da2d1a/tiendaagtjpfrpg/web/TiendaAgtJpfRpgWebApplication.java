package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackages = {
                "es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.security",
                "es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.web",
                "es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common"
        }
)
// Añadimos las entidades de security
@EntityScan(basePackages = {
        "es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities",
        "es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.security.entities"
})
// Añadimos los repositorios de security
@EnableJpaRepositories(basePackages = {
        "es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.repositories",
        "es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.security.repositories"
})
public class TiendaAgtJpfRpgWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(TiendaAgtJpfRpgWebApplication.class, args);
    }
}