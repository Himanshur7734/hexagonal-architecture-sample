# Hexagonal Architecture Sample

A minimal Product create service demonstrating ports-and-adapters
(hexagonal) architecture with Spring Boot.

## Layout

```
src/main/java/com/algorithmxlr8/hexagonal/
  domain/
    model/              Product - pure entity, no framework code
    port/in/            Inbound port: CreateProductUseCase + its command
    port/out/           Outbound port: ProductRepositoryPort
  application/
    service/            ProductService - implements the inbound port,
                         depends only on the outbound port
  adapter/
    in/web/             Driving adapter: REST controller, request/response DTOs
      mapper/           DTO <-> domain conversion (ProductWebMapper)
      exception/        REST error mapping (GlobalExceptionHandler)
    out/persistence/    Driven adapter: JPA entity, Spring Data repository,
                         and the class that implements ProductRepositoryPort
      mapper/           Entity <-> domain conversion (ProductPersistenceMapper)
  config/               Spring wiring that isn't tied to one adapter
                         (PersistenceConfig: explicit JPA repository/entity scan)
  util/                 Framework-free helpers shared across layers
                         (ValidationUtils: guard clauses used by Product)
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
