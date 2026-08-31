package com.algorithmxlr8.hexagonal.adapter;

import com.algorithmxlr8.hexagonal.application.model.Product;

/**
 * Output port. The application core depends on this abstraction only;
 * it has no idea whether the real implementation is JPA, a plain SQL
 * driver, or an in-memory map.
 */
public interface ProductRepositoryPort {

    Product save(Product product);
}
