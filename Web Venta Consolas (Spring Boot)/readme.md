# ModeGaming - Tienda de consolas

Este proyecto consiste en el desarrollo de una tienda virtual especializada en consolas utilizando **Spring Boot**. La aplicación sigue una estructura multiproyecto (multi-módulo) para separar la lógica de negocio de la interfaz de usuario, la seguridad y la API REST.

## 1. Estructura del Proyecto
El sistema se organiza en un proyecto padre que gestiona los siguientes módulos:

* **Módulo Base (Parent)**: Define la configuración global y gestiona los submódulos.
* **Módulo Common**: Contiene el código compartido, incluyendo entidades, repositorios y servicios base.
* **Módulo Web**: Gestiona la interfaz de usuario (Thymeleaf) y controladores tradicionales.
* **Módulo Security**: Gestiona la seguridad para los administradores y auditoría.
* **Módulo API**: Proporciona servicios REST para interacciones desacopladas.

## 2. Módulo Security
Este módulo centraliza la autenticación y autorización, permitiendo un control estricto sobre las acciones administrativas.

* **components**: Incluye `SecurityConfig` para la configuración del firewall de Spring y `UsuarioDetails` para la gestión de sesiones.
* **entities & repositories**: Maneja la persistencia de `SecurityEvento` para registrar logs de auditoría en la base de datos.
* **services**: `AutenticacionService` y `SecurityEventoService` procesan la lógica de seguridad y el registro de eventos.
* **enums**: `SecurityTipoEvento` define los tipos de acciones monitorizadas (LOGIN, LOGOUT, ACCESO_DENEGADO, etc).

## 3. Módulo API (REST)
Proporciona una interfaz programática utilizando estándares modernos para la comunicación con clientes externos.

### Características:
* **Seguridad Stateless**: Implementada mediante tokens **JWT** (`FiltroAutenticacionJwt` y `JwtService`).
* **Arquitectura DTO**: Se utilizan Objetos de Transferencia de Datos (`ProductoDto`, `CarritoDto`, `AddCartItemDto`) para no exponer las entidades JPA directamente.
* **Mappers**: Uso de `CategoriaMapper` y `ProductoMapper` para la conversión eficiente de datos.
* **Global Exception Handler**: Captura errores de forma centralizada para devolver respuestas JSON estandarizadas.

### Endpoints Principales:

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `POST` | `/api/auth/login` | Intercambia credenciales por un token JWT. |
| `GET` | `/api/productos` | Lista todos los productos (DTO). |
| `POST` | `/api/carrito/items` | Añade un producto al carrito (Requiere JWT). |

## 4. Requisitos Técnicos
* **Lenguaje**: Java 21 (Amazon Corretto).
* **Build System**: Maven.
* **Framework**: Spring Boot 3.x.
* **Seguridad**: Spring Security & JWT.

## 5. Ejecución
1.  Clonar el proyecto: `git clone https://github.com/RubenParienteGomez/Proyectos-Realizados.git`
2.  Importar como proyecto Maven en el IDE.
3.  Ejecutar la clase principal en el módulo de entrada.

### 5.1 Posible error de ejecución
* Si el IDE no detecta los módulos, haz clic derecho en el `pom.xml` del parent y selecciona **Maven > Reload Project**.
* En el menú lateral de Maven, selecciona `Generate Sources and Update Folders For All Projects`.

## 6. Gestión de XML
Para la importación de XML, es fundamental que el campo de identificación sea único. Si el código ya existe en el sistema, la importación se rechazará para evitar duplicados y conflictos de datos.