# Proyecto Cliente-Servidor

Proyecto de chat cliente-servidor desarrollado, usando **sockets** y **multihilo**.

El sistema permite la conexión simultánea de múltiples clientes, la gestión de salas, usuarios, roles (cliente / administrador) y la ejecución de comandos mediante texto.

---

## Características principales

- Arquitectura **cliente-servidor** basada en sockets TCP.
- Servidor **multihilo** usando `ExecutorService`.
- Gestión segura de usuarios con `ConcurrentHashMap`.
- Sistema de **salas** con aforo y tipos (cliente / admin).
- Roles de usuario:
  - **Cliente**
  - **Administrador**
- Comandos de texto para interacción con el servidor.
- Carga de configuración desde archivo `chat.properties`.
- Control de permisos según rol.
- Tiempo de conexión por usuario.
- Soporte para mensajes públicos, privados y globales.
- Apagado remoto del servidor por administradores.

---

## Configuración

El servidor y el cliente leen su configuración desde el archivo:

resources/chat.properties

Ejemplo archivo properties:

SERVIDOR.PUERTO=8080
SERVIDOR.HOST=127.0.0.1
SERVIDOR.THREADS=100
ADMIN.PASSWORD=admin123

## Ejecución:
1. Ejecutamos el servidor
2. Ejecutamos los clientes que queramos

-----------------------

# Comandos disponibles
Comandos de cliente
/ayuda – Muestra la ayuda de comandos.

/quien_soy – Muestra información del usuario.

/salas – Lista las salas disponibles.

/usuarios <sala> – Lista usuarios de una sala.

/unirse <sala> – Cambia de sala.

/ping <usuario> – Comprueba si un usuario está en la misma sala.

/mensaje <mensaje> – Envía mensaje a la sala.

/mensaje <mensaje> <usuario> – Envía mensaje privado.

/abandona – Desconecta del servidor.

Comandos de administrador
/crea <sala> <aforo> – Crea una sala.

/elimina <sala> – Elimina una sala vacía.

/elimina_forzado <sala> – Elimina una sala con usuarios.

/cambia_aforo <sala> <aforo> – Cambia el aforo de una sala.

/info_sala <sala> – Muestra información de una sala.

/info_usuario <usuario> – Muestra información de un usuario.

/mensaje_todos <mensaje> – Envía un mensaje a todos los usuarios.

/expulsa <usuario> <mensaje> – Expulsa a un usuario.

/apaga – Apaga el servidor.