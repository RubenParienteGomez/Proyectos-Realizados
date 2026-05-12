package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.services;

import java.util.List;

import org.springframework.stereotype.Service;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.Producto;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.repositories.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> buscarTodos() {
        return productoRepository.findAll();
    }

    @Override
    public List<Producto> buscarVarios(int cantidad) {
        return productoRepository.findAll()
                .stream()
                .limit(cantidad)
                .toList();
    }

    @Override
    public Producto buscarId(Long id) {
        return productoRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Producto guardar(Producto producto) {
        // Validacion codigo
        if (producto.getCodigo() == null || producto.getCodigo().isBlank()) {
            throw new RuntimeException("El código del producto es obligatorio");
        }

        // Validacion formato EAN
        if (!esEanValido(producto.getCodigo())) {
            throw new RuntimeException("El código tiene que tener un formato EAN-13 válido");
        }

        // Validacion codigo unico. Comprueba que sea un duplicado real
        Producto existente = productoRepository.findByCodigo(producto.getCodigo()).orElse(null);

        if (existente != null) {
            if (producto.getId() == null || !existente.getId().equals(producto.getId())) {
                throw new RuntimeException("Ya existe un producto con ese código");
            }
        }

        // Validacion nombre
        if (producto.getNombre() == null || producto.getNombre().isBlank()) {
            throw new RuntimeException("El nombre del producto es obligatorio");
        }

        // Validacion precio
        if (producto.getPrecio() == null || producto.getPrecio() <= 0) {
            throw new RuntimeException("El precio debe ser mayor que 0");
        }

        // Validacion descuento
        if (producto.getDescuento() < 0 || producto.getDescuento() > 100) {
            throw new RuntimeException("El descuento debe estar entre 0 y 100");
        }

        // Validacion descripción
        if (producto.getDescripcion() == null || producto.getDescripcion().isBlank()) {
            throw new RuntimeException("La descripción del producto es obligatoria");
        }

        return productoRepository.save(producto);
    }

    @Override
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }

    // Metodo
    
    private boolean esEanValido(String ean) {

        // Debe tener 13 dígitos
        if (ean == null || !ean.matches("\\d{13}")) {
            return false;
        }

        int suma = 0;

        // primeros 12 dígitos
        for (int i = 0; i < 12; i++) {
            int digito = Character.getNumericValue(ean.charAt(i));

            if (i % 2 == 1) {
                suma += digito * 3;
            } else {
                suma += digito;
            }
        }

        int controlCalculado = (10 - (suma % 10)) % 10;
        int controlReal = Character.getNumericValue(ean.charAt(12));

        return controlCalculado == controlReal;
    }
}