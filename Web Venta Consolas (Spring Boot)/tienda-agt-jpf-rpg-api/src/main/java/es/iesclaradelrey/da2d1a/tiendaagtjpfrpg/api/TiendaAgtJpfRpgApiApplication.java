package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "es.iesclaradelrey.da2d1a.tiendaagtjpfrpg")
@EntityScan(basePackages = {
        "es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities",
        "es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.security.entities"
})
@EnableJpaRepositories(basePackages = {
        "es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.repositories",
        "es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.security.repositories"
})
public class TiendaAgtJpfRpgApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(TiendaAgtJpfRpgApiApplication.class, args);
    }
}