package com.algorithmxlr8.hexagonal.domain.port.in;

import com.algorithmxlr8.hexagonal.domain.model.Product;

public interface UpdateProductUseCase {
    Product updateProduct(Long id, UpdateProductCommand command);
}
