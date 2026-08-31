# Hexagonal Architecture Sample

A minimal Product create service demonstrating ports-and-adapters
(hexagonal) architecture with Spring Boot.

## Layout

```
src/main/java/com/algorithmxlr8/hexagonal/
  domain/
    model/            Product - pure entity, no framework code
    port/in/          Inbound ports: one interface per use case
    port/out/         Outbound port: ProductRepositoryPort
  application/
    service/          ProductService - implements the inbound ports,
                       depends only on the outbound port
  adapter/
    in/web/           Driving adapter: REST controller, DTOs, error mapping
    out/persistence/  Driven adapter: Spring Data JPA entity, repository,
                       and the class that implements ProductRepositoryPort
```

The dependency rule: `domain` depends on nothing in this project.
`application` depends only on `domain`. Both `adapter` packages depend on
`domain` and `application`, never on each other. Swapping the database
(e.g. JPA to MongoDB) means writing a new class in `adapter/out/persistence`
that implements `ProductRepositoryPort` - nothing in `domain` or
`application` changes.

## Run

```bash
./mvnw spring-boot:run
```

App starts on `http://localhost:8080`. In-memory H2 console at
`http://localhost:8080/h2-console` (JDBC URL `jdbc:h2:mem:productdb`, user
`sa`, empty password).

## API

| Method | Path                | Body                                              |
|--------|---------------------|----------------------------------------------------|
| POST   | /api/products        | `{ "name", "description", "price", "quantity" }`   |
