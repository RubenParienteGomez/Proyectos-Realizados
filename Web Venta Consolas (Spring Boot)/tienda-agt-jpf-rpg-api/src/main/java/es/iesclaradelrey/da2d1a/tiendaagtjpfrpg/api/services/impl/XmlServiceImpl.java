package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.services.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.services.XmlService;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.entities.Producto;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.repositories.CategoriaRepository;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.repositories.MarcaRepository;
import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.common.repositories.ProductoRepository;

@Service
public class XmlServiceImpl implements XmlService {

    private final ProductoRepository productoRepository;
    private final MarcaRepository marcaRepository;
    private final CategoriaRepository categoriaRepository;

    public XmlServiceImpl(ProductoRepository pr, MarcaRepository mr, CategoriaRepository cr) {
        this.productoRepository = pr; this.marcaRepository = mr; this.categoriaRepository = cr;
    }

    @Override
    public void exportarProductosDom(OutputStream os) throws Exception {
        List<Producto> productos = productoRepository.findAll();
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

        Element root = doc.createElement("productos");
        doc.appendChild(root);

        for (Producto p : productos) {
            Element pNode = doc.createElement("producto");
            pNode.setAttribute("id", p.getId().toString());
            
            // Añadir campos básicos
            agregarElemento(doc, pNode, "codigo", p.getCodigo());
            agregarElemento(doc, pNode, "nombre", p.getNombre());
            agregarElemento(doc, pNode, "descripcion", p.getDescripcion());
            agregarElemento(doc, pNode, "precio", String.valueOf(p.getPrecio()));
            agregarElemento(doc, pNode, "stock", String.valueOf(p.getStock()));
            agregarElemento(doc, pNode, "imagen", p.getImagen());

            // Marca (ID y Nombre)
            Element marcaNode = doc.createElement("marca");
            marcaNode.setAttribute("id", p.getMarca().getId().toString());
            marcaNode.setTextContent(p.getMarca().getNombre());
            pNode.appendChild(marcaNode);

            // Categorías (ID y Nombre)
            Element catsNode = doc.createElement("categorias");
            for (Categoria c : p.getCategorias()) {
                Element catNode = doc.createElement("categoria");
                catNode.setAttribute("id", c.getId().toString());
                catNode.setTextContent(c.getNombre());
                catsNode.appendChild(catNode);
            }
            pNode.appendChild(catsNode);
            root.appendChild(pNode);
        }

        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(doc), new StreamResult(os));
    }

    private void agregarElemento(Document doc, Element padre, String etiqueta, String texto) {
        Element el = doc.createElement(etiqueta);
        el.setTextContent(texto);
        padre.appendChild(el);
    }

    @Override
    @Transactional
    public void importarProductosSax(InputStream is) throws Exception {
        SAXParserFactory.newInstance().newSAXParser().parse(is, 
            new ProductosHandler(productoRepository, marcaRepository, categoriaRepository));
    }
}