## <p align="center"> ROLES PRUEBA 2</p>

API Rest desarrollada en Java con Spring Boot para la gestión de usuarios(login y registro), roles, desafíos, evaluaciones, categorias y proyectos.


## Índice

1. [Funcionalidades](#Funcionalidades)
2. [Requerimientos previos](#requerimientos-previos)
3. [Configuración](#configuración)
4. [Tecnologías utilizadas](#tecnologías-utilizadas)
5. [Estructura del proyecto](#estructura-del-proyecto)
6. [Diagrama entidad-relación](#diagrama-entidad-relación)


## Funcionalidades

- [x] Autenticación de usuarios con JWT y Spring Security
  - `POST /login` : Inicia sesión y obtiene un Token JWT.

- [x] Registro de un nuevo usuario
  - Endpoints
    * `POST /users/register` : Crea un nuevo usuario con el rol USER
  - Reglas de negocio
    * Todos los campos son obligatorios, por lo tanto, es necesario verificar si todos los campos se están ingresando correctamente.
    * La API no debe permitir el registro de usuarios duplicados (con el mismo correo) y debe tener al menos un número y una letra mayúscula.
    * Asignar el rol USER por defecto
    * La API debe retornar la información del nuevo usuario y el token

- [x] Mostrar usuarios
  - Endpoints
    * `GET /users` : Muestra todos los usuarios
  - Reglas de negocio
    * Retornar los primeros 10 resultados ordenados por id
    * Devolver todos los atributos menos la contraseña
    * Obtener la respuesta con paginación para controlar el volumen de los datos
    * Solo el rol ADMIN puede obtener todos los usuarios


## Requerimientos previos

- **JDK: Java 17 o superior**
- **Gestor de dependencias: Maven 4.0.0**
- **Spring Boot 3.5.0**
- **Base de datos MySQL o PostgreSQL (cambiar la configuración de application.properties)**

## Configuración 

  1. Clona el repositorio
     
     ```bash
     git clone https://github.com/Luiggi-piero/roles-prueba2.git
     cd roles-prueba2
  2. Configura las variables de entorno para la conexión a la base de datos

     ```yaml
     spring.application.name=rolesprueba
     spring.jpa.hibernate.ddl-auto=update

     #spring.datasource.url=jdbc:mysql://localhost:3306/rolesprueba?useSSL=false&serverTimezone=UTC
     #conexion con postgresql
     spring.datasource.url=jdbc:postgresql://localhost:5432/rolesprueba2_db
     spring.datasource.driver-class-name=org.postgresql.Driver

     spring.datasource.username=${DB_USERNAME}
     spring.datasource.password=${DB_PASSWORD}
     api.security.secret=${JWT_SECRET}

  3. Crea un base de datos vacía con el nombre rolesprueba2_db
  
  4. Ejecuta el proyecto

  5. La aplicación estará disponible en: http://localhost:8080


## Tecnologías utilizadas

- **Spring Boot**: Desarrollo rápido y robusto de aplicaciones.
- **Spring Security y JWT**: Autenticación segura.
- **MySQL y postgreSQL**: Sistema de gestión de bases de datos relacional.          


## Estructura del proyecto

Arquitectura basada en paquetes funcionales, se organizan  las carpetas de acuerdo con las características o módulos de la aplicación (por ejemplo, auth, category, challenge), es un diseño entre aspectos funcionales y principios de Clean Architecture y este tipo de arquitectura agrupa cada módulo con sus propios componentes como controladores, servicios, repositorios y modelos.

      src
      └── main
          ├── java/com/example/skilllinkbackend
          │   ├── config       
          │   |   ├── exceptions       -> Exception handling.
          |   |   ├── responses        -> Response format.
          |   |   └── security         -> Security settings.
          │   ├── features
          │   |   ├── auth             -> Authentication.
          |   |   ├── category
          |   |   |   ├── controller   -> REST controllers.
          |   |   |   ├── dto          -> Data transfer object layer.
          |   |   |   ├── model        -> Domain feature layer.
          |   |   |   ├── repository   -> Data persistence layer.
          |   |   |   └── service      -> Business logic layer.
          |   |   ├── challenge   
          |   |   ├── evaluation  
          |   |   ├── project
          |   |   |   ├── controller   -> REST controllers.
          |   |   |   ├── dto          -> Data transfer object layer.
          |   |   |   ├── model        -> Domain feature layer.
          |   |   |   ├── repository   -> Data persistence layer.
          |   |   |   ├── service      -> Business logic layer. 
          |   |   |   └── validations  -> Creation and editing validations.      
          |   |   ├── role   
          |   |   └── usuario   
          └── resources
              └── application.properties -> Configuration app.
        

## Diagrama Entidad Relación
![Image](https://github.com/user-attachments/assets/6245abde-f646-489e-8a19-79ae01cc57ff)

</br>

> [!IMPORTANT]
> * Con sql crea los roles: ADMIN, USER, MENTEE y MENTOR en la tabla roles
> * Cambia a enabled 1 todos los roles
> * Registra un usuario
> * Modifica la tabla users_roles: agrega un registro para asignar al usuario creado el rol ADMIN
> * Agrega la configuración de la bd en application.properties
> * Para aquellos que no tienen la zona horaria GMT-5 modificar el archivo ...TokenService (para indicar la expiración del token)
         

</br>
<p align="center">
  <img src="https://img.shields.io/badge/java-white?style=for-the-badge&logo=openjdk&logoColor=white&labelColor=black">
  <img src="https://img.shields.io/badge/SPRINGBOOT-white?style=for-the-badge&logo=spring&logoColor=white&labelColor=%236DB33F">
  <img src="https://img.shields.io/badge/mysql-white?style=for-the-badge&logo=mysql&logoColor=white&labelColor=4169E1">
  <img src="https://img.shields.io/badge/postgresql-white?style=for-the-badge&logo=postgresql&logoColor=white&labelColor=4169E1">
</p>
