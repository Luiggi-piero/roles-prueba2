## <p align="center"> ROLES PRUEBA 2</p>
![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)<br>
API Rest desarrollada en Java con Spring Boot para la gestión de usuarios(login y registro), roles, desafíos, evaluaciones, categorias y proyectos.


## Índice

1. [Funcionalidades](#Funcionalidades)
2. [Requerimientos previos](#requerimientos-previos)
3. [Configuración](#configuración)
4. [Swagger](#swagger)
5. [Tecnologías utilizadas](#tecnologías-utilizadas)
6. [Estructura del proyecto](#estructura-del-proyecto)
7. [Modelo entidad-relación](#modelo-entidad-relación)
8. [Licencia](#licencia)


## Funcionalidades



<details>
<summary>🔐 Autenticación</summary>

| Método | Endpoint | Reglas de negocio |
|--------|----------|-------------------|
| POST   | `/login` | Inicia sesión y obtiene un Token JWT. |

</details>



<details>
<summary>👤 Usuarios</summary>

| Método | Endpoint          | Reglas de negocio |
|--------|-------------------|-------------------|
| POST   | `/users/register` | - Todos los campos son obligatorios, por lo tanto, es necesario verificar si todos los campos se están ingresando correctamente.<br>- La API no debe permitir el registro de usuarios duplicados (con el mismo correo) y debe tener al menos un número y una letra mayúscula.<br>- Asignar el rol USER por defecto.<br>- La API debe retornar la información del nuevo usuario y el token. |
| GET    | `/users`          | - Retornar los primeros 10 resultados ordenados por id.<br>- Devolver todos los atributos menos la contraseña.<br>- Obtener la respuesta con paginación para controlar el volumen de los datos.<br>- Solo el rol ADMIN puede obtener todos los usuarios. |

</details>



<details>
<summary>🗂️ Categorías de Proyectos</summary>

| Método  | Endpoint                | Reglas de negocio |
|---------|-------------------------|-------------------|
| POST    | `/categories`           | - Retornar la información de la categoría creada.<br>- En el header retorna el path para obtener la categoría.<br>- Si el nombre no se completa mostrar un error 400.<br>- Si la creación fue exitosa retornar un 201. |
| GET     | `/categories`           | - Lectura paginada de los registros.<br>- Por defecto el tamaño de la página es de 10.<br>- Por defecto el ordenamiento es por id. |
| GET     | `/categories/{id}`      | - Si la categoría no existe retornar un 404. |
| UPDATE  | `/categories/{id}`      | - Si la categoría no existe retornar un 404.<br>- Si el nombre no se completa mostrar un error 400. |
| DELETE  | `/categories/{id}`      | - Si la categoría no existe retornar un un código HTTP 404.<br>- Si la eliminación fue exitosa retornar un código HTTP 204 No Content.<br>- Realizar una eliminación lógica. |

</details>



<details>
<summary>🎯 Desafíos</summary>

| Método  | Endpoint              | Reglas de negocio |
|---------|-----------------------|-------------------|
| POST    | `/challenges`         | - Si algún campo obligatorio no se completa retornar un código HTTP 400.<br>- Si el id del creador no existe retornar un código HTTP 404.<br>- Retornar la información del desafío creado.<br>- En el header retorna el path para obtener el desafío.<br>- Solo el rol MENTOR puede crear un desafío.<br>- Si la creación fue exitosa retornar un 201. |
| GET     | `/challenges`         | - Retorno paginado.<br>- Por defecto el tamaño de la página es de 10.<br>- Por defecto el ordenamiento es por el id. |
| GET     | `/challenges/{id}`    | - Si el desafío no existe retornar un código HTTP 404. |
| UPDATE  | `/challenges/{id}`    | - Si el desafío no existe retornar un código HTTP 404.<br>- Si algún campo obligatorio no se completa retornar un 400.<br>- Si el usuario relacionado al id del creador no existe retornar un 404. |
| DELETE  | `/challenges/{id}`    | - Si el desafío no existe retornar un código HTTP 404.<br>- Si la eliminación es exitosa retornar un 204.<br>- Realizar una eliminación lógica. |

</details>



<details>
<summary>📝 Evaluaciones</summary>

| Método  | Endpoint                  | Reglas de negocio |
|---------|---------------------------|-------------------|
| POST    | `/evaluations`            | - Si algún campo obligatorio no se completa retornar un código HTTP 400.<br>- El puntaje debe estar en el rango de 1 a 5.<br>- Si el usuario relacionado al id del evaluador no existe retornar un 404.<br>- Si el usuario relacionado al id del evaluado no existe retornar un 404.<br>- Si el desafío relacionado al id del mismo no existe retornar un 404.<br>- Si la creación fue exitosa retornar la información de la evaluación.<br>- Si la creación fue exitosa en la cabecera indicar la URI al nuevo recurso.<br>- Si la creación fue exitosa retornar un 201. |
| GET     | `/evaluations`            | - Retorno paginado.<br>- Por defecto el tamaño de la página es de 10.<br>- Por defecto el ordenamiento es por el id. |
| GET     | `/evaluations/{id}`       | - Si la evaluación no existe retornar un 404. |
| UPDATE  | `/evaluations/{id}`       | - Si algún campo obligatorio no se completa retornar un código HTTP 400.<br>- El puntaje debe estar en el rango de 1 a 5.<br>- Si la evaluación no existe retornar un 404.<br>- Si el usuario relacionado al id del evaluador no existe retornar un 404.<br>- Si el usuario relacionado al id del evaluado no existe retornar un 404.<br>- Si el desafío relacionado al id del mismo no existe retornar un 404.<br>- Si la edición fue exitosa retornar la información de la evaluación. |
| DELETE  | `/evaluations/{id}`       | - Si la evaluación no existe retornar un código HTTP 404.<br>- Si la eliminación es exitosa retornar un 204.<br>- Realizar una eliminación lógica. |

</details>



<details>
<summary>💼 Proyectos</summary>

| Método  | Endpoint              | Reglas de negocio |
|---------|-----------------------|-------------------|
| POST    | `/projects`           | - Si algún campo obligatorio no se completa mostrar un error 400.<br>- Si el usuario relacionado al id del creador no existe retornar un 404.<br>- Si algún correo del lista de miembros no existe retornar un 404.<br>- Si algún id del lista de categorías no existe retornar un 404.<br>- Si la creación fue exitosa retornar un 201.<br>- Si la creación fue exitosa en la cabecera indicar la URI al nuevo recurso. |
| GET     | `/projects`           | - Retorno paginado.<br>- Por defecto el tamaño de la página es de 10.<br>- Por defecto el ordenamiento es por el id. |
| GET     | `/projects/{id}`      | - Si el proyecto no existe retornar un 404. |
| POST    | `/projects/{id}`      | - Si el proyecto no existe retornar un 404.<br>- Si algún campo obligatorio no se completa mostrar un error 400.<br>- Si el usuario relacionado al id del creador no existe retornar un 404.<br>- Si algún correo del lista de miembros no existe retornar un 404.<br>- Si algún id del lista de categorías no existe retornar un 404. |
| DELETE  | `/projects/{id}`      | - Si el proyecto no existe retornar un código HTTP 404.<br>- Si la eliminación es exitosa retornar un 204.<br>- Realizar una eliminación lógica. |

</details>


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

## Swagger
Swagger está configurado para generar documentación de la API automáticamente. Puedes acceder a la interfaz de Swagger en la siguiente URL cuando el servidor esté en funcionamiento:
```
http://localhost:8080/swagger-ui/index.html
```
![image](https://github.com/user-attachments/assets/1583800c-2286-49b7-bbf9-928a588debf4)


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
        

## Modelo Entidad Relación
![Image](https://github.com/user-attachments/assets/40b0faa5-45e0-4032-b767-053adccf1fb5)

</br>

## Licencia
Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.
</br></br>

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
