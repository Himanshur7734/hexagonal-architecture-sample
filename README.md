# Hexagonal Architecture Sample

A minimal Product create service demonstrating ports-and-adapters
(hexagonal) architecture with Spring Boot.

## Layout

```
src/main/java/com/algorithmxlr8/hexagonal/
  adapter/                Ports only - the interfaces everything else talks through
    CreateProductUseCase.java     Inbound port (the use case), takes a Product directly
    ProductRepositoryPort.java    Outbound port (persistence contract)

  domain/                 All implementation logic that fulfils the ports
    service/
      ProductService.java         Implements CreateProductUseCase
    rest/
      controller/
        ProductController.java    Implements the REST entrypoint
      dto/                        Request/response types
      mapper/                     DTO <-> domain conversion
      exception/                  REST error mapping
    persistence/
      ProductPersistenceAdapter.java  Implements ProductRepositoryPort
    config/                  Spring wiring (JPA repository/entity scan)
    util/                    Framework-free helpers (validation guard clauses)

  application/             Data-holding types: entity, model, repo, mapper
    model/
      Product.java               Domain model used by the ports and services
    entity/
      ProductJpaEntity.java      JPA entity
    repo/
      ProductJpaRepository.java  Spring Data repository interface
    mapper/
      ProductPersistenceMapper.java  Product <-> ProductJpaEntity conversion
```

The rule: `adapter` only ever declares contracts - both ports take or
return the `Product` domain model directly, no separate command/DTO type
in between. `domain` is where every contract gets implemented - service,
controller, persistence adapter, the web mapper, config, util.
`application` holds the plain data types those implementations pass
around (the JPA entity, the domain model, the Spring Data repository) and
the mapper that converts between them, since it only ever touches those
two types. Swapping persistence technology means changing
`domain/persistence` and everything under `application` - `adapter` and
`domain/service` never change.

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
