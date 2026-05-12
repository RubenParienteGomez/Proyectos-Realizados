import java.io.*;
import java.net.*;
import java.util.concurrent.*;


public class Servidor {

    public static final ConcurrentHashMap<String, ManejadorCliente> clientesConectados = new ConcurrentHashMap<>();
    protected static final ConcurrentHashMap<String, Salas> salas = new ConcurrentHashMap<>();
    private static volatile boolean servidorActivo = true;


    public static void main(String[] args) {

        int puerto = ConfigProperties.getInt("SERVIDOR.PUERTO", 8080);
        int maxHilos = ConfigProperties.getInt("SERVIDOR.THREADS", 100);
        crearSalasIniciales();


        //Lo pongo porque ExecutorService no rechaza conexiones, las pone en cola aunque el maximo de hilos este alcanzado
        ExecutorService poolHilos = Executors.newFixedThreadPool(maxHilos);

        try (ServerSocket servidor = new ServerSocket(puerto)) {

            System.out.println("Servidor iniciado en el puerto " + puerto);

            while (servidorActivo) {
                Socket socket = servidor.accept();
                System.out.println("Nueva conexión desde: " + socket.getInetAddress());
                // Crear manejador y lanzarlo en el pool
                ManejadorCliente manejador = new ManejadorCliente(socket);
                poolHilos.execute(manejador);        
            }

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        } finally {
            poolHilos.shutdown();
        }
    }

    //Comprueba si el nombre de usuario esta en uso
    public static synchronized boolean nombreDisponible(String nombre) {
        return !clientesConectados.containsKey(nombre);
    }

    //Registra un nuevo cliente
    public static synchronized void registrarCliente(String nombre, ManejadorCliente manejador) {
        clientesConectados.put(nombre, manejador);
    }

    //Elimina un cliente
    public static synchronized void eliminarCliente(String nombre) {
        clientesConectados.remove(nombre);
    }

    private static void crearSalasIniciales() {
        salas.put("recepcion", new Salas("recepcion", 100, TipoSala.CLIENTE));
        salas.put("jiuston", new Salas("jiuston", 50, TipoSala.ADMIN));
    }

    public static Salas getSala(String nombre) {
        return salas.get(nombre);
    }

    public static boolean existeSala(String nombre) {
        return salas.containsKey(nombre);
    }

    //Metodo para unir a una sala. Revisa si la sala existe, si es admin y si esta llena
    public static synchronized String unirASala(ManejadorCliente cliente, String nombreSala) {
        Salas nuevaSala = salas.get(nombreSala);

        if (nuevaSala == null) {
            return "La sala no existe.";
        }

        if (nuevaSala.getTipo() == TipoSala.ADMIN && !cliente.esAdmin()) {
            return "No tienes permisos para entrar en esta sala.";
        }

        if (nuevaSala.estaLlena()) {
            return "La sala está llena.";
        }

        // Sale de la sala actual
        Salas actual = cliente.getSalaActual();
        if (actual != null) {
            actual.eliminarUsuario(cliente.getNombreUsuario());
        }

        // Entra en la nueva sala
        nuevaSala.agregarUsuario(cliente.getNombreUsuario());
        cliente.setSalaActual(nuevaSala);

        return "Te has unido a la sala '" + nombreSala + "'";
        }

    //Metodo para enviar un mensaje a todos los usuarios en la misma sala. Revisa si esta en una sala antes de enviarlo
    public static void mensajeSala(ManejadorCliente emisor, String mensaje) {

        Salas sala = emisor.getSalaActual();

        if (sala == null) {
            emisor.getSalida().println("No estás en ninguna sala.");
            return;
        }

        for (String usuario : sala.getUsuarios()) {

            if (usuario.equals(emisor.getNombreUsuario())) {
                continue;
            }

            ManejadorCliente receptor = clientesConectados.get(usuario);
            if (receptor != null) {
                receptor.getSalida().println(
                    "[" + sala.getNombre() + "] "
                    + emisor.getNombreUsuario() + ": "
                    + mensaje
                );
            }
        }
    }

    //Metodo para enviar un mensaje privado a otro usuario en la misma sala. Revisa si el usuario existe y si esta en la misma sala
    public static void mensajePrivado(ManejadorCliente emisor, String destinatario, String mensaje) {
        if (destinatario.equals(emisor.getNombreUsuario())) {
            emisor.getSalida().println("No puedes enviarte mensajes a ti mismo.");
            return;
        }

        ManejadorCliente receptor = clientesConectados.get(destinatario);

        if (receptor == null) {
            emisor.getSalida().println("El usuario no existe.");
            return;
        }

        if (receptor.getSalaActual() != emisor.getSalaActual()) {
            emisor.getSalida().println("El usuario no está en tu sala.");
            return;
        }

        receptor.getSalida().println(
            "[PRIVADO] " + emisor.getNombreUsuario() + ": " + mensaje
        );
    }

    //Metodo para hacer ping a un usuario en la misma sala. Revisa si el usuario existe y si esta en la misma sala
    public static void pingUsuario(ManejadorCliente emisor, String nombreUsuario) {

        if (nombreUsuario == null || nombreUsuario.isEmpty()) {
            emisor.getSalida().println("Uso: /ping <usuario>");
            return;
        }

        ManejadorCliente receptor = clientesConectados.get(nombreUsuario);

        if (receptor == null) {
            emisor.getSalida().println("El usuario no existe.");
            return;
        }

        if (receptor.getSalaActual() != emisor.getSalaActual()) {
            emisor.getSalida().println("El usuario no está en tu sala.");
            return;
        }

        emisor.getSalida().println(
            "PING EXISTOSO: " + nombreUsuario + " está en la sala '" + emisor.getSalaActual().getNombre() + "'"
        );
    }

    //Metodo para crear una sala. Revisa permisos, existencia de sala y valor del aforo
    public static void crearSala(PrintWriter salida, ManejadorCliente cliente, String nombre, int aforo) {
        if (!cliente.esAdmin()) {
            salida.println("No tienes permisos para crear salas.");
            return;
        }

        if (salas.containsKey(nombre)) {
            salida.println("La sala ya existe.");
            return;
        }

        if (aforo <= 0) {
            salida.println("El aforo debe ser mayor que 0.");
            return;
        }

        salas.put(nombre, new Salas(nombre, aforo, TipoSala.CLIENTE));
        salida.println("Sala '" + nombre + "' creada correctamente.");
    }

    //Metodo para expulsar a un usuario del servidor. Revisa permisos de admin y si el usuario existe y no es admin
    public static void expulsarUsuario(ManejadorCliente admin, String usuario, String mensaje) {
        if (!admin.esAdmin()) {
            admin.getSalida().println("No tienes permisos para expulsar usuarios.");
            return;
        }

        ManejadorCliente expulsado = clientesConectados.get(usuario);

        if (expulsado == null) {
            admin.getSalida().println("El usuario no existe.");
            return;
        }

        if (expulsado.esAdmin()) {
            admin.getSalida().println("No se puede expulsar a un administrador.");
            return;
        }

        // Mensaje al usuario expulsado
        if (mensaje != null && !mensaje.isEmpty()) {
            expulsado.getSalida().println("Has sido expulsado del servidor.");
            expulsado.getSalida().println("Motivo: " + mensaje);
        } else {
            expulsado.getSalida().println("Has sido expulsado del servidor.");
        }

        // Cerrar conexión
        try {
            expulsado.getSalida().close();
        } catch (Exception ignored) {}

        admin.getSalida().println("Usuario '" + usuario + "' expulsado correctamente.");
    }

    //Metodo para apagar el servidor. Revisa permisos de admin antes de proceder
    public static void apagarServidor(ManejadorCliente admin) {

        if (!admin.esAdmin()) {
            admin.getSalida().println("No tienes permisos para apagar el servidor.");
            return;
        }

        for (ManejadorCliente cliente : clientesConectados.values()) {
            cliente.getSalida().println("El servidor ha sido apagado por un administrador.");
            try {
                cliente.getSalida().close();
            } catch (Exception ignored) {}
        }

        clientesConectados.clear();
        servidorActivo = false;

        admin.getSalida().println("Servidor apagado correctamente.");
    }

    //Metodo para listar los usuarios en una sala. Revisa permisos y existencia de sala. Si la sala es ADMIN y el solicitante no es admin, no puede verla
    public static void listarUsuariosSala(PrintWriter salida, ManejadorCliente solicitante, String nombreSala) {
        if (nombreSala == null || nombreSala.isEmpty()) {
            salida.println("Uso: /usuarios <sala>");
            return;
        }

        Salas sala = salas.get(nombreSala);

        if (sala == null) {
            salida.println("La sala '" + nombreSala + "' no existe.");
            return;
        }

        // Si es sala ADMIN y el solicitante no es admin no puede verla
        if (sala.getTipo() == TipoSala.ADMIN && !solicitante.esAdmin()) {
            salida.println("No tienes permisos para ver los usuarios de esta sala.");
            return;
        }

        salida.println("Usuarios en la sala '" + nombreSala + "':");

        if (sala.getUsuarios().isEmpty()) {
            salida.println("(La sala está vacía)");
            return;
        }

        for (String usuario : sala.getUsuarios()) {
            salida.println("- " + usuario);
        }
    }

    //Metodo para crear una sala. Revisa permisos, existencia de sala y valor del aforo
    public static void crearSala(ManejadorCliente admin, String nombre, int aforo) {
        if (!admin.esAdmin()) {
            admin.getSalida().println("No tienes permisos para crear salas.");
            return;
        }

        if (nombre.equalsIgnoreCase("recepcion")
            || nombre.equalsIgnoreCase("jiuston")) {
            admin.getSalida().println("No se puede crear una sala reservada.");
            return;
        }

        if (salas.containsKey(nombre)) {
            admin.getSalida().println("La sala ya existe.");
            return;
        }

        if (aforo <= 0) {
            admin.getSalida().println("El aforo debe ser mayor que 0.");
            return;
        }

        salas.put(nombre, new Salas(nombre, aforo, TipoSala.CLIENTE));
        admin.getSalida().println("Sala '" + nombre + "' creada correctamente.");
    }

    //Metodo para eliminar una sala. Revisa permisos, existencia de sala y si la sala tiene usuarios dentro
    public static void eliminarSala(PrintWriter salida, ManejadorCliente admin, String nombreSala) {

        if (!admin.esAdmin()) {
            salida.println("No tienes permisos para eliminar salas.");
            return;
        }

        if (nombreSala == null || nombreSala.isEmpty()) {
            salida.println("Uso: /elimina <sala>");
            return;
        }

        if (nombreSala.equals("recepcion") || nombreSala.equals("jiuston")) {
            salida.println("No se puede eliminar una sala protegida.");
            return;
        }

        Salas sala = salas.get(nombreSala);

        if (sala == null) {
            salida.println("La sala no existe.");
            return;
        }

        if (!sala.getUsuarios().isEmpty()) {
            salida.println("La sala tiene usuarios dentro. No se puede eliminar.");
            return;
        }

        salas.remove(nombreSala);
        salida.println("Sala '" + nombreSala + "' eliminada correctamente.");
    }

    //Metodo para cambiar el aforo de una sala. Revisa permisos, existencia de sala, valor del aforo y si el nuevo aforo es menor que los usuarios actuales
    public static void cambiarAforo(PrintWriter salida, ManejadorCliente admin, String nombreSala, int aforo) {

        if (!admin.esAdmin()) {
            salida.println("No tienes permisos para cambiar aforo.");
            return;
        }

        Salas sala = salas.get(nombreSala);

        if (sala == null) {
            salida.println("La sala no existe.");
            return;
        }

        if (aforo <= 0) {
            salida.println("El aforo debe ser mayor que 0.");
            return;
        }

        if (aforo < sala.getNumeroUsuarios()) {
            salida.println("El aforo es menor que los usuarios actuales.");
            return;
        }

        sala.setAforo(aforo);
        salida.println("Aforo de la sala '" + nombreSala + "' actualizado a " + aforo);
    }

    //Metodo para eliminar una sala forzosamente, moviendo a los usuarios a recepcion. Revisa permisos, existencia de sala y aforo de recepcion
    public static void eliminarSalaForzada(PrintWriter salida, ManejadorCliente admin, String nombreSala) {

        if (!admin.esAdmin()) {
            salida.println("No tienes permisos.");
            return;
        }

        if (nombreSala.equals("recepcion") || nombreSala.equals("jiuston")) {
            salida.println("No se puede eliminar una sala protegida.");
            return;
        }

        Salas sala = salas.get(nombreSala);

        if (sala == null) {
            salida.println("La sala no existe.");
            return;
        }

        Salas recepcion = salas.get("recepcion");

        if (recepcion.getNumeroUsuarios() + sala.getNumeroUsuarios() > recepcion.getAforo()) {
            salida.println("No caben los usuarios en recepción.");
            return;
        }

        for (String usuario : sala.getUsuarios()) {
            ManejadorCliente c = clientesConectados.get(usuario);
            if (c != null) {
                recepcion.agregarUsuario(usuario);
                c.setSalaActual(recepcion);
                c.getSalida().println("Has sido movido a recepción.");
            }
        }

        salas.remove(nombreSala);
        salida.println("Sala '" + nombreSala + "' eliminada forzosamente.");
    }

    //Metodo para mostrar la informacion de una sala. Revisa si la sala existe antes de mostrarla
    public static void infoSala(PrintWriter salida, String nombreSala) {

        Salas sala = salas.get(nombreSala);

        if (sala == null) {
            salida.println("La sala no existe.");
            return;
        }

        salida.println("Sala: " + sala.getNombre());
        salida.println("Creada: " + sala.getFechaCreacion());
        salida.println("Usuarios: " + sala.getNumeroUsuarios());
        salida.println("Aforo: " + sala.getAforo());
    }

    //Metodo para mostrar la informacion de un usuario. Revisa si el usuario existe antes de mostrarlo
    public static void infoUsuario(PrintWriter salida, String nombre) {

        ManejadorCliente c = clientesConectados.get(nombre);

        if (c == null) {
            salida.println("El usuario no existe.");
            return;
        }

        salida.println("Usuario: " + c.getNombreUsuario());
        salida.println("Rol: " + c.getNombreRol());
        salida.println("Sala: " + c.getSalaActual().getNombre());
        salida.println("Tiempo conectado: " + c.getTiempoConectado() + " segundos");
    }

    //Metodo para enviar un mensaje a todos los usuarios conectados. Revisa si tiene permisos de admin antes de enviarlo
    public static void mensajeTodos(ManejadorCliente admin, String mensaje) {

        if (!admin.esAdmin()) {
            admin.getSalida().println("No tienes permisos.");
            return;
        }

        for (ManejadorCliente c : clientesConectados.values()) {
            c.getSalida().println("[SERVIDOR] " + mensaje);
        }
    }
}