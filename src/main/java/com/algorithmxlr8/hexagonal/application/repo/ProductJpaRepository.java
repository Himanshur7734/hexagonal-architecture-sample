package com.algorithmxlr8.hexagonal.application.repo;

import com.algorithmxlr8.hexagonal.application.entity.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, Long> {
}
