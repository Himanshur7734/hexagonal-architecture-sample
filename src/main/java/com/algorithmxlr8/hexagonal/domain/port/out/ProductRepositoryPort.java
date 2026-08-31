package com.algorithmxlr8.hexagonal.domain.port.out;

import com.algorithmxlr8.hexagonal.domain.model.Product;

import java.util.List;
import java.util.Optional;

/**
 * Output port. The application core depends on this abstraction only;
 * it has no idea whether the real implementation is JPA, a plain SQL
 * driver, or an in-memory map.
 */
public interface ProductRepositoryPort {

    Product save(Product product);

    Optional<Product> findById(Long id);

    List<Product> findAll();
}
