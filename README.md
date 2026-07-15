# Gestión de Usuarios - Spring Boot + Thymeleaf

Proyecto Spring Boot para gestión de usuarios con CRUD completo, vistas Thymeleaf y manejo global de excepciones.

## Características

- CRUD completo de usuarios (Crear, Leer, Actualizar, Eliminar)
- Vistas dinámicas con Thymeleaf
- Manejo global de excepciones con `@ControllerAdvice`
- Validación de formularios con Jakarta Validation
- Persistencia con Spring Data JPA + MySQL
- Páginas de error personalizadas (404, 400, 500)
- Diseño responsivo con CSS integrado

## Tecnologías

- **Java 21**
- **Spring Boot 3.4.1**
- **Spring Web MVC**
- **Spring Data JPA**
- **Thymeleaf**
- **MySQL Connector**
- **Lombok**
- **Maven**

## Requisitos Previos

- Java 21 o superior
- Maven 3.6+
- MySQL 8.0+

## Configuración de Base de Datos

1. Crear base de datos en MySQL:
```sql
CREATE DATABASE proyectspringexec;
```

2. Configurar credenciales en `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/proyectspringexec
spring.datasource.username=root
spring.datasource.password=tu_contraseña
```

## Instalación y Ejecución

### Compilar el proyecto
```bash
mvn clean compile
```

### Ejecutar la aplicación
```bash
mvn spring-boot:run
```

La aplicación estará disponible en: http://localhost:8080

### Empaquetar para producción
```bash
mvn clean package
java -jar target/gestion-usuarios-0.0.1-SNAPSHOT.jar
```

## Uso

### Endpoints Principales

| Método | URL | Descripción |
|--------|-----|-------------|
| GET | `/usuarios` | Lista todos los usuarios |
| GET | `/usuarios/nuevo` | Formulario para crear usuario |
| POST | `/usuarios` | Crear nuevo usuario |
| GET | `/usuarios/{id}` | Ver detalle de usuario |
| GET | `/usuarios/{id}/editar` | Formulario para editar usuario |
| POST | `/usuarios/{id}` | Actualizar usuario |
| POST | `/usuarios/{id}/eliminar` | Eliminar usuario |

### Demostración de Exception Handling

La página principal incluye botones para probar el manejo de excepciones:
- `/usuarios/error/404` - Simula recurso no encontrado
- `/usuarios/error/400` - Simula solicitud incorrecta
- `/usuarios/error/500` - Simula error interno del servidor

## Estructura del Proyecto

```
src/main/java/com/example/gestionusuarios/
├── GestionUsuariosApplication.java
├── model/
│   └── Usuario.java
├── repository/
│   └── UsuarioRepository.java
├── service/
│   └── UsuarioService.java
├── controller/
│   └── UsuarioController.java
└── exception/
    ├── ResourceNotFoundException.java
    └── GlobalExceptionHandler.java

src/main/resources/
├── application.properties
└── templates/
    ├── usuarios/
    │   ├── list.html
    │   ├── form.html
    │   └── detail.html
    └── error/
        ├── 404.html
        ├── 400.html
        └── 500.html
```

## Manejo de Excepciones

El proyecto implementa manejo global de excepciones con `@ControllerAdvice`:

### Excepciones Manejadas

1. **ResourceNotFoundException** → HTTP 404
   - Cuando se busca un recurso que no existe
   - Vista: `error/404.html`

2. **IllegalArgumentException** → HTTP 400
   - Cuando hay datos inválidos o duplicados
   - Vista: `error/400.html`

3. **Exception** (genérica) → HTTP 500
   - Errores inesperados del sistema
   - Vista: `error/500.html`

### Flujo de Excepciones

```
1. Controller/Service lanza excepción
2. Spring intercepta la excepción
3. GlobalExceptionHandler busca el @ExceptionHandler apropiado
4. Se ejecuta el handler correspondiente
5. Se renderiza vista de error personalizada
6. Usuario ve página de error amigable (no stack trace)
```

## Modelo de Datos

### Usuario
- `id` (Long) - Identificador único
- `nombre` (String) - Nombre del usuario (2-100 caracteres)
- `email` (String) - Email único con formato válido
- `password` (String) - Contraseña (mínimo 6 caracteres)
- `activo` (boolean) - Estado del usuario

## Pruebas

Ejecutar tests unitarios:
```bash
mvn test
```

## Consideraciones de Seguridad

- Las contraseñas se almacenan en texto plano (solo para demostración)
- En producción, implementar:
  - Spring Security
  - Encriptación de contraseñas con BCrypt
  - HTTPS
  - Protección CSRF
  - Validación de entrada adicional

## Próximas Mejoras

- Implementar Spring Security
- Agregar paginación en lista de usuarios
- Implementar búsqueda y filtros
- Agregar roles y permisos
- Implementar API REST además de MVC
- Agregar tests unitarios e integración
- Implementar logging con SLF4J
- Agregar métricas con Spring Boot Actuator

## Licencia

Este proyecto es solo para fines educativos y de demostración.

## Autor

Proyecto desarrollado como ejemplo de Spring Boot con Thymeleaf y manejo global de excepciones.
