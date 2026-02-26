# Examen Final Backend

Este repositorio contiene el backend para el Examen Final del Proyecto Final EPN.

## Arquitectura del Proyecto

El proyecto sigue una arquitectura en capas típica de aplicaciones Spring Boot, organizada para separar responsabilidades y facilitar el mantenimiento y la escalabilidad.

La estructura de paquetes principal se encuentra en `src/main/java/com/examen/backend` y consta de los siguientes componentes:

*   **`controller`**: Capa de controladores REST. Aquí se definen los endpoints de la API que reciben las peticiones HTTP y devuelven las respuestas.
*   **`model`**: Núcleo de la lógica de negocio y persistencia. Se subdivide en:
    *   **`entity`**: Entidades JPA que representan los datos del negocio y su mapeo a la base de datos.
    *   **`repository`**: Interfaces de acceso a datos que abstraen la interacción con la base de datos.
    *   **`service`**: Contiene la lógica de negocio y las reglas de la aplicación.
*   **`dto` (Data Transfer Objects)**: Objetos utilizados para transportar datos entre procesos, especialmente útiles para desacoplar la capa de presentación (controladores) de la capa de persistencia.
*   **`config`**: Clases de configuración del proyecto, como configuraciones de seguridad, beans de Spring, o configuraciones de base de datos.
*   **`utility`**: Clases de utilidad y helpers que proporcionan funciones comunes reutilizables en toda la aplicación. Incluye `JwtUtil` para la generación y validación de tokens JWT seguros.

## Requisitos

*   Java 17 o superior
*   Maven

## Configuración

Antes de ejecutar la aplicación, es necesario configurar el archivo `application.properties` en `src/main/resources`. Este archivo no se incluye en el repositorio por seguridad.

Asegúrate de definir las siguientes propiedades:

```properties
# Configuración de Base de Datos
spring.datasource.url=jdbc:postgresql://localhost:5432/tu_base_de_datos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.datasource.driver-class-name=org.postgresql.Driver

# Configuración de JWT
# Debe ser una cadena Base64 válida de al menos 512 bits para HS512
jwt.secret=TU_CLAVE_SECRETA_EN_BASE64
```

## Cómo ejecutar

Para ejecutar la aplicación, utiliza el siguiente comando en la raíz del proyecto:

```bash
./mvnw spring-boot:run
```

## Despliegue

Asegúrate de configurar correctamente las variables de entorno o el archivo de propiedades en el servidor de despliegue.
