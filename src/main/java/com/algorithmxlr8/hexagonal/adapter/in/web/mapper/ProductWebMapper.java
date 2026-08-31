package com.algorithmxlr8.hexagonal.adapter.in.web.mapper;

import com.algorithmxlr8.hexagonal.adapter.in.web.dto.ProductRequest;
import com.algorithmxlr8.hexagonal.adapter.in.web.dto.ProductResponse;
import com.algorithmxlr8.hexagonal.domain.model.Product;
import com.algorithmxlr8.hexagonal.domain.port.in.command.CreateProductCommand;
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
