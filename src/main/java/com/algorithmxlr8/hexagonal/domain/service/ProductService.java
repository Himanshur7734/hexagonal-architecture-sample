package com.algorithmxlr8.hexagonal.domain.service;

import com.algorithmxlr8.hexagonal.adapter.CreateProductUseCase;
import com.algorithmxlr8.hexagonal.adapter.ProductRepositoryPort;
import com.algorithmxlr8.hexagonal.adapter.command.CreateProductCommand;
import com.algorithmxlr8.hexagonal.application.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implements the inbound port. This is the only class that knows both
 * "what a use case is" and "how to reach the outbound port" - adapters
 * on either side never talk to each other directly.
 */
@Service
@Transactional
public class ProductService implements CreateProductUseCase {

    private final ProductRepositoryPort productRepository;

    public ProductService(ProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(CreateProductCommand command) {
        Product product = Product.createNew(command.name(), command.description(), command.price(), command.quantity());
        return productRepository.save(product);
    }
}
