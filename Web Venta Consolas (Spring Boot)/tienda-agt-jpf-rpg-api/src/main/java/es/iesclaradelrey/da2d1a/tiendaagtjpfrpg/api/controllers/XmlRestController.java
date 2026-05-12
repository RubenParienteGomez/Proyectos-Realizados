package es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.iesclaradelrey.da2d1a.tiendaagtjpfrpg.api.services.XmlService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/xml")
public class XmlRestController {

    private final XmlService xmlService;

    public XmlRestController(XmlService xmlService) {
        this.xmlService = xmlService;
    }

    @GetMapping
    public void exportar(HttpServletResponse response) throws Exception {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd.HH:mm"));
        String nombreFichero = "products-export." + timestamp + ".xml";

        response.setContentType("application/xml");
        response.setCharacterEncoding("UTF-8"); 
        response.setHeader("Content-Disposition", "attachment; filename=" + nombreFichero); 

        xmlService.exportarProductosDom(response.getOutputStream());
    }

    @PostMapping
    public void importar(@RequestParam("productsfile") MultipartFile file) throws Exception {
        //
        xmlService.importarProductosSax(file.getInputStream());
    }
}