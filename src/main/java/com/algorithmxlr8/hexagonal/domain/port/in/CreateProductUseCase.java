package com.algorithmxlr8.hexagonal.domain.port.in;

import com.algorithmxlr8.hexagonal.domain.model.Product;
import com.algorithmxlr8.hexagonal.domain.port.in.command.CreateProductCommand;

public interface CreateProductUseCase {
    Product createProduct(CreateProductCommand command);
}
