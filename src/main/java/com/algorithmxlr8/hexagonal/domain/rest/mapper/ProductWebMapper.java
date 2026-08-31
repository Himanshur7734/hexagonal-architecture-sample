package com.algorithmxlr8.hexagonal.domain.rest.mapper;

import com.algorithmxlr8.hexagonal.application.model.Product;
import com.algorithmxlr8.hexagonal.domain.rest.dto.ProductRequest;
import com.algorithmxlr8.hexagonal.domain.rest.dto.ProductResponse;

public class ProductWebMapper {

    public Product toDomain(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        return product;
    }

    public ProductResponse toResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());
        return response;
    }
}
