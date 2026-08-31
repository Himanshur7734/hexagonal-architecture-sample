package com.hexagonal.domain.service;

import com.hexagonal.adapter.CreateProductPort;
import com.hexagonal.adapter.ProductRepositoryPort;
import com.hexagonal.application.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService implements CreateProductPort {

    private final ProductRepositoryPort productRepository;

    public ProductService(ProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}
