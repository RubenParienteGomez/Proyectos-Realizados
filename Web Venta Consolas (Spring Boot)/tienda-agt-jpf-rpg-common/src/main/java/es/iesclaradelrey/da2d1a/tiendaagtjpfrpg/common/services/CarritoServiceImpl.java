package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.services;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.*;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.exceptions.ProductoNoEncontradoException;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.exceptions.StockInsuficienteException;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public CarritoItem addProducto(String email, Long productoId, Integer unidades) {
        // Validar que no sea null y sea > 0
        if (unidades == null) {
            throw new IllegalArgumentException("El campo 'unidades' es obligatorio en el JSON");
        }
        if (unidades <= 0) {
            throw new IllegalArgumentException("Las unidades deben ser mayores a cero");
        }

        // Buscar producto y usuario
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new ProductoNoEncontradoException(productoId));

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));

        // Verificar stock (Si no lanza StockInsuficienteException Error 409)
        if (producto.getStock() < unidades) {
            throw new StockInsuficienteException("Stock insuficiente para el producto: " + producto.getNombre());
        }

        CarritoItemId id = new CarritoItemId(usuario.getId(), producto.getId());

        CarritoItem item = carritoRepository.findById(id)
                .orElse(new CarritoItem(usuario, producto, 0, LocalDateTime.now()));

        item.setUnidades(item.getUnidades() + unidades);
        item.setFechaActualizacion(LocalDateTime.now());

        return carritoRepository.save(item);
    }

    @Override
    public List<CarritoItem> getCarritoByUsuario(String email) {
        return carritoRepository.findByUsuarioEmail(email);
    }

    @Override
    public int getNumProductosDistintos(String email) {
        return carritoRepository.countDistinctProductosByEmail(email);
    }

    @Override
    public int getUnidadesTotales(String email) {
        Integer unidades = carritoRepository.sumUnidadesByEmail(email);
        return (unidades == null) ? 0 : unidades;
    }

    @Override
    public Double getImporteTotal(String email) {
        Double total = carritoRepository.calculateImporteTotalByEmail(email);
        return (total == null) ? 0.0 : total;
    }

    @Override
    public void eliminarProducto(String email, Long productoId) {
        // Verificar si el producto existe en la base de datos
        if (!productoRepository.existsById(productoId)) {
            throw new ProductoNoEncontradoException(productoId);
        }

        // Buscar los items del usuario
        List<CarritoItem> items = carritoRepository.findByUsuarioEmail(email);

        // Verificar si el producto está en el carro (409 si no está)
        CarritoItem aEliminar = items.stream()
                .filter(i -> i.getProducto().getId().equals(productoId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("CONFLIT_ERROR: El producto no está en el carrito"));

        carritoRepository.delete(aEliminar);
    }

    @Override
    public void vaciarCarrito(String email) {

        carritoRepository.deleteByUsuarioEmail(email);
    }
}