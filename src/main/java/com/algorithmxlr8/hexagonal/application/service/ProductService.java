package com.algorithmxlr8.hexagonal.application.service;

import com.algorithmxlr8.hexagonal.domain.exception.ProductNotFoundException;
import com.algorithmxlr8.hexagonal.domain.model.Product;
import com.algorithmxlr8.hexagonal.domain.port.in.CreateProductCommand;
import com.algorithmxlr8.hexagonal.domain.port.in.CreateProductUseCase;
import com.algorithmxlr8.hexagonal.domain.port.in.DeleteProductUseCase;
import com.algorithmxlr8.hexagonal.domain.port.in.GetProductUseCase;
import com.algorithmxlr8.hexagonal.domain.port.in.ListProductsUseCase;
import com.algorithmxlr8.hexagonal.domain.port.in.UpdateProductCommand;
import com.algorithmxlr8.hexagonal.domain.port.in.UpdateProductUseCase;
import com.algorithmxlr8.hexagonal.domain.port.out.ProductRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implements every inbound port. This is the only class that knows both
 * "what a use case is" and "how to reach the outbound port" - adapters
 * on either side never talk to each other directly.
 */
@Service
@Transactional
public class ProductService implements CreateProductUseCase, GetProductUseCase, ListProductsUseCase,
        UpdateProductUseCase, DeleteProductUseCase {

    private final ProductRepositoryPort productRepository;

    public ProductService(ProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(CreateProductCommand command) {
        Product product = Product.createNew(command.name(), command.description(), command.price(), command.quantity());
        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, UpdateProductCommand command) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        product.updateDetails(command.name(), command.description(), command.price(), command.quantity());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }
}
