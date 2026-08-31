package com.hexagonal.adapter;

import com.hexagonal.application.model.Product;

public interface CreateProductPort {
    
    Product createProduct(Product product);
}
