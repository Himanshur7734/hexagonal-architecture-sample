package com.algorithmxlr8.hexagonal.domain.web.mapper;

import com.algorithmxlr8.hexagonal.adapter.command.CreateProductCommand;
import com.algorithmxlr8.hexagonal.application.model.Product;
import com.algorithmxlr8.hexagonal.domain.web.dto.ProductRequest;
import com.algorithmxlr8.hexagonal.domain.web.dto.ProductResponse;
import org.springframework.stereotype.Component;

/**
 * Converts between web DTOs and domain types. Kept out of the controller
 * and out of the DTOs themselves so request/response mapping has one
 * dedicated home, mirroring the persistence mapper on the outbound side.
 */
@Component
public class ProductWebMapper {

    public CreateProductCommand toCommand(ProductRequest request) {
        return new CreateProductCommand(request.name(), request.description(), request.price(), request.quantity());
    }

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(),
                product.getPrice(), product.getQuantity());
    }
}
