import java.io.*;
import java.util.Properties;

public class ConfigProperties {
    
    private static final Properties propiedades = new Properties();

    static {
        cargarPropiedades();
    }

   private static void cargarPropiedades() {
    try (InputStream input = new FileInputStream("resources/chat.properties")) {
        propiedades.load(input);
    } catch (IOException e) {
        System.err.println("No se pudo cargar chat.properties, usando valores por defecto.");
    }
}

    //Getter para devolver una propiedad
    public static String getPropiedades(String clave, String defecto) {
        return propiedades.getProperty(clave, defecto);
    }

    //Getter para devolver una propiedad como entero, usando un valor por defecto si no existe o no es valida
    public static int getInt(String clave, int defecto) {
        try {
            return Integer.parseInt(propiedades.getProperty(clave));
        } catch (Exception e) {
            return defecto;
        }
    }
}
