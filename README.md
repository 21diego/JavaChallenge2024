# Proyecto Nivelacion Tribu Java Shark Accenture



## Tecnologías utilizadas

- **Spring Boot**: Framework principal para crear aplicaciones Java, usando dependencia de Spring Web para la creacion de API.
- **Spring Data JPA**: Para gestionar la persistencia de datos utilizando JPA.
- **JUnit 5**: Para realizar pruebas unitarias.
- **Mockito**: Para crear mocks en las pruebas unitarias.
- **Docker**: Para contenerizar la aplicación y la base de datos MySQL.
- **MySQL**: Base de datos relacional utilizada para almacenar las acreditaciones.
- **Java 17**: Version utilizada para el desarrollo de este proyecto

## Como iniciar el proyecto

Para levantar la app, se tiene que levantar la API a traves del IDE o ejecutar el comando:

`java -jar .\PuntosDeVentaAPI-1.0.0.jar`

Para levantar la base de mysql se ejecuta:

`docker-compose up`

La configuracion esta en un docker-compose.yml (Hubo un intento de dockerizar toda la app pero pasaron cosas)

## Comentarios

- Se incluye una coleccion de pruebas en postman para probar cada endpoint de la app: `Puntos de Ventas collection.postman_collection.json`
- El algoritmo de Dijkstra es copiada de una implementacion en Java de Internet ya adaptada a los objetos que he creado para el proyecto (La teoria de algoritmos y grafos la tenia pero como implementarlo no 🤣)
