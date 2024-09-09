# Sistema de Gestión de Libros

Este proyecto es una aplicación de escritorio desarrollada en Java utilizando Spring Boot y Java Swing para gestionar libros en una base de datos MySQL. La aplicación ofrece una interfaz gráfica que permite listar, agregar, actualizar y eliminar libros.

## Características

- **Listar Libros**: Muestra una tabla con todos los libros almacenados en la base de datos.
- **Agregar Libro**: Permite agregar un nuevo libro a la base de datos.
- **Actualizar Libro**: Permite actualizar la información de un libro existente.
- **Eliminar Libro**: Permite eliminar un libro de la base de datos.

## Tecnologías Utilizadas

- **Spring Boot**: Para la creación de la aplicación.
- **Spring Data JPA**: Para la interacción con la base de datos.
- **MySQL**: Como sistema de gestión de base de datos.
- **Java Swing**: Para la interfaz gráfica de usuario.
- **Lombok**: Para simplificar el modelo de datos.

## Instrucciones de Uso

1. Clona el repositorio.

```sh
   git clone https://github.com/gianpoker1/Tienda-Libros.git
   cd Tienda-Libros
```
  
2. Configura la base de datos MySQL con las credenciales proporcionadas en `application.properties`.

```
spring.datasource.url=jdbc:mysql://localhost:3306/books_db
spring.datasource.username=root
spring.datasource.password=admin
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

2. Ejecuta el siguiente comando para compilar la aplicación en un terminal en el directorio del proyecto
```
mvn clean package
```
**Este comando generará un archivo JAR en el directorio target.**

3. Ejecutar la Aplicación.
  Una vez compilada la aplicación, ejecuta el siguiente comando para iniciar la aplicación:

```
java -jar target/Management_Book-1.0-SNAPSHOT.jar
```



#### Estructura del Proyecto


El proyecto sigue la estructura típica de una aplicación Spring Boot. Aquí tienes una descripción de los paquetes y clases principales:

- **com.managementBook.Management_Book**:
  - `BookForm.java`: Clase principal que define la interfaz gráfica utilizando Java Swing.

- **com.managementBook.Management_Book.model**:
  - `Book.java`: Clase de entidad que representa a un libro. Utiliza Lombok para simplificar el código.

- **com.managementBook.Management_Book.repository**:
  - `BookRepository.java`: Interfaz que extiende `JpaRepository` para realizar operaciones CRUD en la base de datos.

- **com.managementBook.Management_Book.service**:
  - `IBookService.java`: Interfaz que define los métodos del servicio de libros.
  - `BookService.java`: Implementación de `IBookService` que utiliza `BookRepository` para interactuar con la base de datos.


## Uso de la Aplicación

Una vez que la aplicación esté en funcionamiento, se mostrará una interfaz gráfica con una tabla que lista todos los libros almacenados en la base de datos. La interfaz incluye los siguientes botones:

- **Agregar**: Permite agregar un nuevo libro a la base de datos.
- **Actualizar**: Permite actualizar la información de un libro existente.
- **Eliminar**: Permite eliminar un libro de la base de datos.
- **Limpiar**: Limpia el formulario de entrada.

### Ejemplo de Uso

1. **Agregar un Libro**:
   - Rellena los campos de nombre, autor, precio y existencias.
   - Haz clic en el botón "Agregar".

2. **Actualizar un Libro**:
   - Selecciona un libro de la tabla.
   - Modifica los campos necesarios.
   - Haz clic en el botón "Actualizar".

3. **Eliminar un Libro**:
   - Selecciona un libro de la tabla.
   - Haz clic en el botón "Eliminar".

4. **Limpiar el Formulario**:
   - Haz clic en el botón "Limpiar" para resetear los campos del formulario.


## Dependencias

El proyecto utiliza las siguientes dependencias principales:

- **Spring Boot**: Framework para desarrollar aplicaciones Java.
- **Spring Data JPA**: Para la interacción con la base de datos.
- **MySQL**: Sistema de gestión de base de datos.
- **Java Swing**: Para la interfaz gráfica de usuario.
- **Lombok**: Para simplificar el modelo de datos.
