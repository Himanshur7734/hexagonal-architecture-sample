package com.algorithmxlr8.hexagonal.domain.port.in;

import com.algorithmxlr8.hexagonal.domain.model.Product;

import java.util.List;

public interface ListProductsUseCase {
    List<Product> listProducts();
}
