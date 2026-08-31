package com.algorithmxlr8.hexagonal.adapter;

import com.algorithmxlr8.hexagonal.adapter.command.CreateProductCommand;
import com.algorithmxlr8.hexagonal.application.model.Product;

public interface CreateProductUseCase {
    Product createProduct(CreateProductCommand command);
}
