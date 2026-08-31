package com.algorithmxlr8.hexagonal.domain.persistence;

import com.algorithmxlr8.hexagonal.adapter.ProductRepositoryPort;
import com.algorithmxlr8.hexagonal.application.entity.ProductJpaEntity;
import com.algorithmxlr8.hexagonal.application.model.Product;
import com.algorithmxlr8.hexagonal.application.mapper.ProductPersistenceMapper;
import com.algorithmxlr8.hexagonal.application.repo.ProductJpaRepository;
import org.springframework.stereotype.Component;

@Component
class ProductPersistenceAdapter implements ProductRepositoryPort {

    private final ProductJpaRepository jpaRepository;
    private final ProductPersistenceMapper mapper;

    ProductPersistenceAdapter(ProductJpaRepository jpaRepository, ProductPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Product save(Product product) {
        ProductJpaEntity entity = mapper.toEntity(product);
        ProductJpaEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }
}
