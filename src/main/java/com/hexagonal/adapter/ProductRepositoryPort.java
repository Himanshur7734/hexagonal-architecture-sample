package com.hexagonal.adapter;

import com.hexagonal.application.model.Product;

public interface ProductRepositoryPort {

    Product save(Product product);
}
