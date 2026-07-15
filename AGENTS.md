# Gestión de Usuarios - Spring Boot

## Descripción del Proyecto
Proyecto Spring Boot para gestión de usuarios con CRUD completo, Thymeleaf y manejo global de excepciones.

## Stack Tecnológico
- **Java 21**
- **Spring Boot 3.4.1**
- **Spring Data JPA**
- **Thymeleaf**
- **MySQL** (base de datos: `proyectspringexec`)
- **Maven**
- **Lombok**

## Comandos Principales

### Compilar proyecto
```bash
mvn clean compile
```

### Ejecutar aplicación
```bash
mvn spring-boot:run
```

### Ejecutar tests
```bash
mvn test
```

### Empaquetar aplicación
```bash
mvn clean package
```

### Ejecutar JAR generado
```bash
java -jar target/gestion-usuarios-0.0.1-SNAPSHOT.jar
```

## Estructura del Proyecto
```
src/main/java/com/example/gestionusuarios/
├── GestionUsuariosApplication.java       # Clase principal
├── model/
│   └── Usuario.java                      # Entidad JPA
├── repository/
│   └── UsuarioRepository.java            # Spring Data JPA
├── service/
│   └── UsuarioService.java               # Lógica de negocio
├── controller/
│   └── UsuarioController.java            # Controlador MVC
└── exception/
    ├── ResourceNotFoundException.java    # Excepción personalizada
    └── GlobalExceptionHandler.java       # Manejo global de excepciones
```

## Convenciones de Código
- Usar Lombok para reducir boilerplate (@Data, @NoArgsConstructor, @AllArgsConstructor)
- Inyección de dependencias via constructor (@RequiredArgsConstructor)
- Validaciones con Jakarta Validation (@NotBlank, @Email, @Size)
- Excepciones personalizadas para errores de negocio
- GlobalExceptionHandler centraliza el manejo de errores

## Base de Datos
- **URL**: jdbc:mysql://localhost:3306/proyectspringexec
- **Usuario**: root
- **DDL Auto**: update (crea/actualiza tablas automáticamente)

## Endpoints Principales
- `GET /usuarios` - Lista todos los usuarios
- `GET /usuarios/nuevo` - Formulario crear usuario
- `POST /usuarios` - Crear usuario
- `GET /usuarios/{id}` - Ver detalle de usuario
- `GET /usuarios/{id}/editar` - Formulario editar usuario
- `POST /usuarios/{id}` - Actualizar usuario
- `POST /usuarios/{id}/eliminar` - Eliminar usuario

## Exception Handling
- **ResourceNotFoundException** → 404 (Recurso no encontrado)
- **IllegalArgumentException** → 400 (Solicitud incorrecta)
- **Exception** → 500 (Error interno del servidor)

## Notas Importantes
- La aplicación crea automáticamente la tabla `usuarios` al iniciar
- Thymeleaf cache deshabilitado para desarrollo
- H2 Console deshabilitado (usando MySQL)
