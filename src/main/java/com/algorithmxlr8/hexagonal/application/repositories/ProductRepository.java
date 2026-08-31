package com.algorithmxlr8.hexagonal.application.repositories;

import com.algorithmxlr8.hexagonal.application.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
