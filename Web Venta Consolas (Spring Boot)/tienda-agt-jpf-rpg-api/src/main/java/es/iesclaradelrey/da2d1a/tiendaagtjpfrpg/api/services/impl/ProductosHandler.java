package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.Marca;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.Producto;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.repositories.CategoriaRepository;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.repositories.MarcaRepository;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.repositories.ProductoRepository;

public class ProductosHandler extends DefaultHandler {
    private final ProductoRepository productoRepo;
    private final MarcaRepository marcaRepo;
    private final CategoriaRepository categoriaRepo;

    private Producto productoActual;
    private StringBuilder textoLineal = new StringBuilder();
    private List<Categoria> categoriasActuales;

    public ProductosHandler(ProductoRepository pr, MarcaRepository mr, CategoriaRepository cr) {
        this.productoRepo = pr; this.marcaRepo = mr; this.categoriaRepo = cr;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        textoLineal.setLength(0);
        if (qName.equals("producto")) {
            productoActual = new Producto();
            categoriasActuales = new ArrayList<>();
        } else if (qName.equals("marca")) {
            Long id = Long.parseLong(attributes.getValue("id"));
            // Validar marca o lanzar error
            Marca m = marcaRepo.findById(id).orElseThrow(() -> new RuntimeException("Marca con ID " + id + " no encontrada"));
            productoActual.setMarca(m);
        } else if (qName.equals("categoria")) {
            Long id = Long.parseLong(attributes.getValue("id"));
            // Validar categoría o lanzar error
            Categoria c = categoriaRepo.findById(id).orElseThrow(() -> new RuntimeException("Categoría con ID " + id + " no encontrada"));
            categoriasActuales.add(c);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        textoLineal.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "codigo" -> productoActual.setCodigo(textoLineal.toString());
            case "nombre" -> productoActual.setNombre(textoLineal.toString());
            case "precio" -> productoActual.setPrecio(Double.parseDouble(textoLineal.toString()));
            case "stock" -> productoActual.setStock(Integer.parseInt(textoLineal.toString()));
            case "descripcion" -> productoActual.setDescripcion(textoLineal.toString());
            case "producto" -> {
                productoActual.setCategorias(categoriasActuales);
                productoRepo.save(productoActual);
            }
        }
    }
}