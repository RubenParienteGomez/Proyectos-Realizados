    package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.repositories;

    import java.util.List;
    import java.util.Optional;

    import org.springframework.data.jpa.repository.JpaRepository;

    import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.Producto;

    // Se crea el repositorio especifico
    public interface ProductoRepository extends JpaRepository<Producto, Long> {
        boolean existsByCodigo(String codigo);
        Optional<Producto> findByCodigo(String codigo);
        List<Producto> findByCategorias_Id(Long categoriaId, org.springframework.data.domain.Sort sort);
    }
