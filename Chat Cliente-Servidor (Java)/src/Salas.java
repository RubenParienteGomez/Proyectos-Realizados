import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Salas {
   private final String nombre;
   private int aforo;
   private final LocalDateTime fechaCreacion;
   private final Set<String> usuarios;
   private final TipoSala tipo;

   public Salas(String nombre, int aforo, TipoSala tipo) {
      this.nombre = nombre;
      this.aforo = aforo;
      this.tipo = tipo;
      this.fechaCreacion = LocalDateTime.now();
      this.usuarios = ConcurrentHashMap.newKeySet();
   }

   public String getNombre() {
      return this.nombre;
   }

   public int getAforo() {
      return this.aforo;
   }

   public void setAforo(int nuevoAforo) {
      this.aforo = nuevoAforo;
   }

   public TipoSala getTipo() {
      return this.tipo;
   }

   public LocalDateTime getFechaCreacion() {
      return this.fechaCreacion;
   }

   public Set<String> getUsuarios() {
      return this.usuarios;
   }

   public int getNumeroUsuarios() {
      return this.usuarios.size();
   }

   public boolean estaLlena() {
      return this.usuarios.size() >= this.aforo;
   }

   public boolean agregarUsuario(String usuario) {
      return this.estaLlena() ? false : this.usuarios.add(usuario);
   }

   public void eliminarUsuario(String usuario) {
      this.usuarios.remove(usuario);
   }

   // Metodo para listar las salas disponibles, mostrando solo las salas ADMIN si el usuario es admin. Uso un iterador para recorrer las salas
   public static void listarSalas(PrintWriter salida, boolean esAdmin) {
      salida.println("Salas disponibles:");
      Iterator var3 = Servidor.salas.values().iterator();

      while(true) {
         Salas sala;
         do {
            if (!var3.hasNext()) {
               return;
            }

            sala = (Salas)var3.next();
         } while(sala.getTipo() == TipoSala.ADMIN && !esAdmin);

         String var10001 = sala.getNombre();
         salida.println("- " + var10001 + " (" + sala.getNumeroUsuarios() + "/" + sala.getAforo() + ")");
      }
   }

  
}