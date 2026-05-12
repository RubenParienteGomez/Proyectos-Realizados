import java.io.*;
import java.net.Socket;

public class ManejadorCliente implements Runnable {

    private final Socket socket;
    private String nombreUsuario;
    private RolUsuario rol;
    private long tiempoConexion;
    private Salas salaActual;
    private PrintWriter salida;

    public ManejadorCliente(Socket socket) {
        this.socket = socket;
        this.rol = new RolCliente();
        this.tiempoConexion = System.currentTimeMillis();
    }

    //Getters

    public boolean esAdmin() {
        return rol.esAdmin();
    }

    public String getNombreRol() {
        return rol.getNombreRol();
    }

    public long getTiempoConectado() {
        return (System.currentTimeMillis() - tiempoConexion) / 1000;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public Salas getSalaActual() {
        return salaActual;
    }

    public void setSalaActual(Salas sala) {
        this.salaActual = sala;
    }

    public PrintWriter getSalida() {
        return salida;
    }

    public String infoUsuario() {
        String sala = (salaActual != null) ? salaActual.getNombre() : "Ninguna";

        return "Usuario: " + nombreUsuario + "\nRol: " + rol.getNombreRol() + "\nSala actual: " + sala + "\nTiempo conectado: " + getTiempoConectado() + " segundos";
    }


    @Override
    public void run() {
        BufferedReader entrada = null;

        try {
            entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            this.salida = new PrintWriter(
                    socket.getOutputStream(), true
            );

            salida.println("Bienvenido al servidor!");

            String linea;

            //Pedir nombre de usuario
            do {
                salida.println("Introduce tu nombre de usuario:");
                linea = entrada.readLine();

                if (linea == null || linea.trim().isEmpty()) {
                    salida.println("Nombre no válido.");
                    linea = null;
                } else if (!Servidor.nombreDisponible(linea)) {
                    salida.println("Ese nombre ya está en uso.");
                    linea = null;
                }

            } while (linea == null);

            nombreUsuario = linea;
            Servidor.registrarCliente(nombreUsuario, this);

            salaActual = Servidor.getSala("recepcion");
            salaActual.agregarUsuario(nombreUsuario);

            salida.println("Usuario registrado como: " + nombreUsuario);

            //Esta parte del codigo se encarga de pedir si quiere entrar como administrador
            salida.println("¿Desea entrar como administrador? (s/n)");
            String respuesta = entrada.readLine();

            if ("s".equalsIgnoreCase(respuesta)) {
                salida.println("Introduce la contraseña de administrador:");
                String pass = entrada.readLine();

                String passAdmin = ConfigProperties.getPropiedades("ADMIN.PASSWORD", "");

                if (passAdmin.equals(pass)) {
                    rol = new RolAdmin();
                    salida.println("Acceso como administrador concedido.");
                } else {
                    salida.println("Contraseña incorrecta. Entrando como cliente.");
                }
            }

            salida.println("Escribe /abandona para desconectarte.");

            // Bucle principal
            while ((linea = entrada.readLine()) != null) {

                if (linea.startsWith("/")) {
                    boolean continuar = Comandos.procesar(linea, this, salida);
                    if (!continuar) break;
                } else {
                    salida.println("Error: Los mensajes deben comenzar con '/' para ser reconocidos como comandos.");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (nombreUsuario != null) {
                Servidor.eliminarCliente(nombreUsuario);
            }

            if (salaActual != null) {
                salaActual.eliminarUsuario(nombreUsuario);
            }

            System.out.println("[SERVIDOR] Usuario desconectado: " + nombreUsuario);

            try {
                socket.close();
            } catch (IOException ignored) {}
        }
    }
}
