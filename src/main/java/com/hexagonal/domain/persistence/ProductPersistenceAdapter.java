package com.hexagonal.domain.persistence;

import com.hexagonal.adapter.ProductRepositoryPort;
import com.hexagonal.application.entity.ProductEntity;
import com.hexagonal.application.model.Product;
import com.hexagonal.application.mapper.ProductPersistenceMapper;
import com.hexagonal.application.repositories.ProductRepository;
import org.springframework.stereotype.Component;

@Component
class ProductPersistenceAdapter implements ProductRepositoryPort {

    private final ProductRepository jpaRepository;
    private final ProductPersistenceMapper mapper;

    ProductPersistenceAdapter(ProductRepository jpaRepository, ProductPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = mapper.toEntity(product);
        ProductEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }
}
