package com.algorithmxlr8.hexagonal.application.mapper;

import com.algorithmxlr8.hexagonal.application.entity.ProductJpaEntity;
import com.algorithmxlr8.hexagonal.application.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductPersistenceMapper {

    public ProductJpaEntity toEntity(Product product) {
        return new ProductJpaEntity(product.getId(), product.getName(), product.getDescription(),
                product.getPrice(), product.getQuantity());
    }

    public Product toDomain(ProductJpaEntity entity) {
        return new Product(entity.getId(), entity.getName(), entity.getDescription(),
                entity.getPrice(), entity.getQuantity());
    }
}
