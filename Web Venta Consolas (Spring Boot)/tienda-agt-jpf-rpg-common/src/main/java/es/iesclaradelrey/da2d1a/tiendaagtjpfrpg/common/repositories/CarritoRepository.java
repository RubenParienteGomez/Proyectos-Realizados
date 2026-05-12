package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.repositories;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.CarritoItem;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.CarritoItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarritoRepository extends JpaRepository<CarritoItem, CarritoItemId> {
    List<CarritoItem> findByUsuarioEmail(String email);
    void deleteByUsuarioEmail(String email);

    @Query("SELECT COUNT(c) FROM CarritoItem c WHERE c.usuario.email = :email")
    int countDistinctProductosByEmail(@Param("email") String email);

    @Query("SELECT SUM(c.unidades) FROM CarritoItem c WHERE c.usuario.email = :email")
    Integer sumUnidadesByEmail(@Param("email") String email);

    @Query("SELECT SUM((p.precio * (1 - p.descuento / 100.0)) * c.unidades) " +
            "FROM CarritoItem c JOIN c.producto p WHERE c.usuario.email = :email")
    Double calculateImporteTotalByEmail(@Param("email") String email);
}