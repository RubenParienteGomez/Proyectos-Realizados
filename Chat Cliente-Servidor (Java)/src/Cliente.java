import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class Cliente {
    public static void main(String[] args) {

        String host = ConfigProperties.getPropiedades("SERVIDOR.HOST", "127.0.0.1");
        int puerto = ConfigProperties.getInt("SERVIDOR.PUERTO", 12345);

        //Conecto el cliente al servidor a traves de un socket
        try (Socket socket = new Socket(host, puerto);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
            Scanner teclado = new Scanner(System.in)) {

            System.out.println("Conectado al servidor " + host + ":" + puerto);

            // Hilo para leer mensajes del servidor
            Thread lectorServidor = new Thread(() -> {
                try {
                    String linea;
                    while ((linea = entrada.readLine()) != null) {
                        System.out.println("\r" + linea);
                        System.out.print("> ");
                    }
                } catch (IOException e) {
                    System.out.println("\nConexión cerrada por el servidor.");
                }
            });


            lectorServidor.start();

            // Bucle principal: leer teclado y enviar al servidor
            while (true) {
                System.out.print("> ");   // PROMPT
                String texto = teclado.nextLine();
                salida.println(texto);

                if (texto.equalsIgnoreCase("/abandona")) {
                    break;
                }
            }


        } catch (IOException e) {
            System.err.println("No se pudo conectar con el servidor: " + e.getMessage());
        }

        System.out.println("Cliente finalizado.");
    }
}
