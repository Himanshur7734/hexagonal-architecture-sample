# Hexagonal Architecture Sample

A minimal Product create service demonstrating ports-and-adapters
(hexagonal) architecture with Spring Boot.

## Layout

```
src/main/java/com/hexagonal/
  adapter/                Ports only - the interfaces everything else talks through
    CreateProductPort.java        Inbound port (the use case), takes a Product directly
    ProductRepositoryPort.java    Outbound port (persistence contract)

  domain/                 All implementation logic that fulfils the ports
    service/
      ProductService.java         Implements CreateProductPort
    rest/
      controller/
        ProductController.java    REST entrypoint, delegates to ProductHandler
      handler/
        ProductHandler.java       Wires the use case + mapper, keeps mapping out of the controller
      dto/                        Request/response types (plain classes, not records)
      mapper/                     DTO <-> domain conversion
      exception/                  REST error mapping
    persistence/
      ProductPersistenceAdapter.java  Implements ProductRepositoryPort
    config/                  Spring wiring (JPA repository/entity scan, mapper beans)
    util/                    Framework-free helpers (validation guard clauses)

  application/             Data-holding types: entity, model, repository, mapper
    model/
      Product.java               Domain model (plain class, fields + getters/setters)
    entity/
      ProductEntity.java         JPA entity
    repositories/
      ProductRepository.java     Spring Data repository interface
    mapper/
      ProductPersistenceMapper.java  Product <-> ProductEntity conversion
```

The rule: `adapter` only ever declares contracts - both ports take or
return the `Product` domain model directly, no separate command/DTO type
in between. `domain` is where every contract gets implemented - service,
controller, handler, persistence adapter, the web mapper, config, util.
`application` holds the plain data types those implementations pass
around (the JPA entity, the domain model, the Spring Data repository) and
the mapper that converts between them, since it only ever touches those
two types. Every DTO, entity, and the domain model itself is a plain
class with private fields and explicit getters/setters - no records, no
Lombok, no builders. Mappers build the target object with `new` and then
call each setter explicitly, field by field. Neither mapper carries a
Spring stereotype annotation - both are registered as beans explicitly
in `domain/config/MapperConfig.java`.

The controller never touches a mapper directly - it only calls
`ProductHandler`, which owns both the use case and the web mapper.

Swapping persistence technology means changing `domain/persistence` and
everything under `application` - `adapter` and `domain/service` never
change.

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
