
# Prueba creacion de usuario
## descripcion del proyecto

- Este proyecto se contruyo con la version de Spring Boot 2.7.6 y Java 8 U441
- Como gestor de librerias se uso maven.
- Como base de datos en memoria usa H2 o puede usar una base de datos MySql

## Diagrama del proyecto

![Demo System Schematic](https://github.com/hilcer/prueba-nizum/blob/main/images/diagrama.jpg)

## Procedimiento de despliegue

1. Realizar la descarga del sistema con git, ejecutando el siguiente comando:

```bash
  git clone https://github.com/hilcer/prueba-nizum.git
```

2. Dirigirse dentro del directorio (carpeta) api-user/ y ejecute la siguiente linea de comando para construir el proyecto 

```bash
  mvn clean package
```
3. El paso previo creara el archivo: `target/demo-0.0.1-SNAPSHOT.jar`, para iniciar el servicio ejecute el siguiente comando:

```bash
  java -jar target/demo-0.0.1-SNAPSHOT.jar
```
4. El sistema contiene las siguiente variables a configurar, las cuales tienes valores por defecto:

`PATTERN_VALIDATION_PASSWORD` : Patron a configurar para la contraseña.

`SPRING_DATASOURSE_URL` : URL de conexion para la base de datos.

`SPRING_DATASOURSE_USERNAME` : Usuario para conectar a la base de datos.

`SPRING_DATASOURSE_PASSWORD` : Contraseña para conectarse a la base de datos.

Ejemplos de ejecucion con las variables:

Ejecucion para configurar el patron de contraseña.
```bash
  java -jar target/demo-0.0.1-SNAPSHOT.jar --pattern.validation.password="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{5,}$"
```

Ejecucion para conectarse a una base de datos MySQL (Por default el servicio inicia en una base de datos de memoria H2).
En esta ejecucion puede o no usar el script de inicializacion que se encuentra en: `script/initial_script.sql`
```bash
  java -jar target/demo-0.0.1-SNAPSHOT.jar --spring.datasource.username=root --spring.datasource.password=adminadmin --spring.datasource.url=jdbc:mysql://localhost:3306/prueba --spring.jpa.hibernate.ddl-auto=update --spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver --spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```
5. Desde su navegador de preferencia, ingrese al siguiente enlace: http://localhost:8080/swagger-ui.html para realizar las pruebas de los servicios.

