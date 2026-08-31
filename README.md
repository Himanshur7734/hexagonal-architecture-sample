# Hexagonal Architecture Sample

A minimal Product create service demonstrating ports-and-adapters
(hexagonal) architecture with Spring Boot.

## Layout

```
src/main/java/com/algorithmxlr8/hexagonal/
  adapter/                Ports only - the interfaces everything else talks through
    CreateProductUseCase.java     Inbound port (the use case)
    ProductRepositoryPort.java    Outbound port (persistence contract)
    command/
      CreateProductCommand.java   Input type for the inbound port

  domain/                 All implementation logic that fulfils the ports
    service/
      ProductService.java         Implements CreateProductUseCase
    web/
      controller/
        ProductController.java    Implements the REST entrypoint
      dto/                        Request/response types
      mapper/                     DTO <-> domain conversion
      exception/                  REST error mapping
    persistence/
      ProductPersistenceAdapter.java  Implements ProductRepositoryPort
      mapper/                          Entity <-> domain conversion
    config/                  Spring wiring (JPA repository/entity scan)
    util/                    Framework-free helpers (validation guard clauses)

  application/             Data-holding types: entity, model, repo
    model/
      Product.java               Domain model used by the ports and services
    entity/
      ProductJpaEntity.java      JPA entity
    repo/
      ProductJpaRepository.java  Spring Data repository interface
```

The rule: `adapter` only ever declares contracts (interfaces + the
command types their methods take). `domain` is where every contract gets
implemented - services, controller, persistence adapter, mappers, config,
util. `application` holds the plain data types those implementations pass
around (the JPA entity, the domain model, the Spring Data repository).
Swapping persistence technology means changing `domain/persistence` and
`application/entity` + `application/repo` - `adapter` and `domain/service`
never change.

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
