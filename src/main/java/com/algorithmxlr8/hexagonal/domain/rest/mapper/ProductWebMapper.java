package com.algorithmxlr8.hexagonal.domain.rest.mapper;

import com.algorithmxlr8.hexagonal.application.model.Product;
import com.algorithmxlr8.hexagonal.domain.rest.dto.ProductRequest;
import com.algorithmxlr8.hexagonal.domain.rest.dto.ProductResponse;
import org.springframework.stereotype.Component;

/**
 * Converts between web DTOs and domain types. Kept out of the controller
 * and out of the DTOs themselves so request/response mapping has one
 * dedicated home, mirroring the persistence mapper on the outbound side.
 */
@Component
public class ProductWebMapper {

    public Product toDomain(ProductRequest request) {
        return Product.createNew(request.name(), request.description(), request.price(), request.quantity());
    }

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(),
                product.getPrice(), product.getQuantity());
    }
}
