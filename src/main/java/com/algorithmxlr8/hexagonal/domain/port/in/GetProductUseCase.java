package com.algorithmxlr8.hexagonal.domain.port.in;

import com.algorithmxlr8.hexagonal.domain.model.Product;

public interface GetProductUseCase {
    Product getProduct(Long id);
}
