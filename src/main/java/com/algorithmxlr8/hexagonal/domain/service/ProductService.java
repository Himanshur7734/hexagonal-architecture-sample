package com.algorithmxlr8.hexagonal.domain.service;

import com.algorithmxlr8.hexagonal.adapter.CreateProductUseCase;
import com.algorithmxlr8.hexagonal.adapter.ProductRepositoryPort;
import com.algorithmxlr8.hexagonal.application.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService implements CreateProductUseCase {

    private final ProductRepositoryPort productRepository;

    public ProductService(ProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}
