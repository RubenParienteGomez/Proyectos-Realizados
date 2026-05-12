import java.io.*;

public class Comandos {

    public static boolean procesar(String linea,ManejadorCliente cliente, PrintWriter salida) {

        //Divide el comando en distintas partes, lo pone en minusculas y coge la primera parte como comando
        String[] partes = linea.trim().split("\\s+");
        String comando = partes[0].toLowerCase();

        //Uso un switch para cada comando
        switch (comando) {

            case "/ayuda":
                mostrarAyuda(salida);
                return true;

            case "/quien_soy":
                salida.println(cliente.infoUsuario());
                return true;

            case "/ping":
                if (partes.length < 2) {
                    salida.println("Uso: /ping <usuario>");
                    return true;
                }
                Servidor.pingUsuario(cliente, partes[1]);
                return true;
            
            case "/salas":
                Salas.listarSalas(salida, cliente.esAdmin());
                return true;

            case "/usuarios":
                if (partes.length < 2) {
                    salida.println("Uso: /usuarios <sala>");
                    return true;
                }
                Servidor.listarUsuariosSala(salida, cliente, partes[1]);
                return true;

            case "/unirse":
                if (partes.length < 2) {
                    salida.println("Uso: /unirse <sala>");
                    return true;
                }

                String nombreSala = partes[1];
                String resultado = Servidor.unirASala(cliente, nombreSala);
                salida.println(resultado);
                return true;
            
            case "/crea":
                if (partes.length < 2) {
                    salida.println("Uso: /crea <sala> [aforo]");
                    return true;
                }

                int aforo = (partes.length >= 3) ? Integer.parseInt(partes[2]): 50;

                Servidor.crearSala(cliente, partes[1], aforo);
                return true;

            case "/elimina":
                if (partes.length < 2) {
                    salida.println("Uso: /elimina <sala>");
                    return true;
                }
                Servidor.eliminarSala(salida, cliente, partes[1]);
                return true;

            case "/cambia_aforo":
                if (partes.length < 3) {
                    salida.println("Uso: /cambia_aforo <sala> <aforo>");
                    return true;
                }
                try {
                    int cambioAforo = Integer.parseInt(partes[2]);
                    Servidor.cambiarAforo(salida, cliente, partes[1], cambioAforo);
                } catch (NumberFormatException e) {
                    salida.println("El aforo debe ser un número.");
                }
                return true;

            
            case "/mensaje":
                if (partes.length < 2) {
                    salida.println("Uso: /mensaje <mensaje> [usuario]");
                    return true;
                }

                if (partes.length >= 3) {
                    String usuario = partes[partes.length - 1];
                    String mensajePrivado = linea.substring(
                        comando.length(),
                        linea.lastIndexOf(usuario)).trim();

                    Servidor.mensajePrivado(cliente, usuario, mensajePrivado);
                } else {
                    String mensaje = linea.substring(comando.length()).trim();
                    Servidor.mensajeSala(cliente, mensaje);
                }
                return true;

            case "/expulsa":
                if (partes.length < 2) {
                    salida.println("Uso: /expulsa <usuario> [mensaje]");
                    return true;
                }

                String usuario = partes[1];
                String mensaje = "";

                if (partes.length > 2) {
                    mensaje = linea.substring(linea.indexOf(usuario) + usuario.length()).trim();
                }

                Servidor.expulsarUsuario(cliente, usuario, mensaje);
                return true;
            
            case "/elimina_forzado":
                if (partes.length < 2) {
                    salida.println("Uso: /elimina_forzado <sala>");
                    return true;
                }
                Servidor.eliminarSalaForzada(salida, cliente, partes[1]);
                return true;

            case "/info_sala":
                if (partes.length < 2) {
                    salida.println("Uso: /info_sala <sala>");
                    return true;
                }
                Servidor.infoSala(salida, partes[1]);
                return true;

            case "/info_usuario":
                if (partes.length < 2) {
                    salida.println("Uso: /info_usuario <usuario>");
                    return true;
                }
                Servidor.infoUsuario(salida, partes[1]);
                return true;

            case "/mensaje_todos":
                if (partes.length < 2) {
                    salida.println("Uso: /mensaje_todos <mensaje>");
                    return true;
                }
                String msg = linea.substring(comando.length()).trim();
                Servidor.mensajeTodos(cliente, msg);
                return true;

            case "/abandona":
                salida.println("Desconectando del servidor");
                return false;

            case "/apaga":
                Servidor.apagarServidor(cliente);
                return false; // corta el bucle del admin

            default:
                salida.println("Comando no reconocido. Usa /ayuda");
                return true;
        }
    }

    //Muestra la ayuda leyendo el archivo comandos.txt
    private static void mostrarAyuda(PrintWriter salida) {
        try (BufferedReader br = new BufferedReader(new FileReader("resources/comandos.txt"))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                salida.println(linea);
            }

        } catch (IOException e) {
            salida.println("No se pudo cargar la ayuda");
        }
    }

}
