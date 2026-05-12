package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.services;

import java.io.InputStream;
import java.io.OutputStream;

public interface XmlService {
    // DOM
    void exportarProductosDom(OutputStream os) throws Exception;
    
    // SAX
    void importarProductosSax(InputStream is) throws Exception;
}