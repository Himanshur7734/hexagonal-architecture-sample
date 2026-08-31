package com.algorithmxlr8.hexagonal.adapter;

import com.algorithmxlr8.hexagonal.application.model.Product;

public interface ProductRepositoryPort {

    Product save(Product product);
}
