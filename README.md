# WishList REST

REST API using Java, Spring Boot, PostgreSQL

### How to run

### PostgreSQL/H2

To access PostgreSQL, spin up PostreSQL database in Docker.

```
docker run --name postgresql-container -p 5432:5432 -e POSTGRES_PASSWORD=abc -d postgres

Project Configuration 

URL: jdbc:postgresql://localhost:5432/
User Name: postgres
Password: abc
```

Default database is Postgres but, there is H2 in-memory database available.  
Navigate to ```scr/main/resources/application.properties ```

Comment out:

```wish-list.store-type=postgres```

And uncomment:

```
wish-list.store-type=h2
spring.h2.console.enabled=true  
```

To access H2:

```
Browser: http://localhost:8080/h2-console/

Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:mydb
User Name: sa
Password: 
```

To run Spring Boot application. In project root directory

```shell
mvn spring-boot:run
```

### Available endpoints

#### Wish

```
http://localhost:8080/wish/add  
http://localhost:8080/wish/update  
http://localhost:8080/wish/delete  
http://localhost:8080/wish/get  
http://localhost:8080/wish/get-all
```

#### User

```
http://localhost:8080/users/names
```

### Manual testing

Start the project and head to   
```http://localhost:8080/swagger-ui/index.html```  
```'Try it out' -> fill out necessary fields -> 'Execute'```
