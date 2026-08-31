package com.algorithmxlr8.hexagonal.adapter.in.web.dto;

import com.algorithmxlr8.hexagonal.domain.model.Product;

import java.math.BigDecimal;

public record ProductResponse(Long id, String name, String description, BigDecimal price, Integer quantity) {

    public static ProductResponse from(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(),
                product.getPrice(), product.getQuantity());
    }
}
