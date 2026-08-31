package com.algorithmxlr8.hexagonal.adapter.out.persistence;

import com.algorithmxlr8.hexagonal.domain.model.Product;
import com.algorithmxlr8.hexagonal.domain.port.out.ProductRepositoryPort;
import org.springframework.stereotype.Component;

/**
 * The concrete driven adapter for ProductRepositoryPort. Spring wires this
 * in wherever the port is required; swapping persistence technology means
 * writing a new adapter here, not touching the domain or application layer.
 */
@Component
class ProductPersistenceAdapter implements ProductRepositoryPort {

    private final ProductJpaRepository jpaRepository;

    ProductPersistenceAdapter(ProductJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Product save(Product product) {
        ProductJpaEntity entity = toEntity(product);
        ProductJpaEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    private ProductJpaEntity toEntity(Product product) {
        return new ProductJpaEntity(product.getId(), product.getName(), product.getDescription(),
                product.getPrice(), product.getQuantity());
    }

    private Product toDomain(ProductJpaEntity entity) {
        return new Product(entity.getId(), entity.getName(), entity.getDescription(),
                entity.getPrice(), entity.getQuantity());
    }
}
