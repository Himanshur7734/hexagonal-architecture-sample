package com.algorithmxlr8.hexagonal.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, Long> {
}
