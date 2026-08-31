package com.algorithmxlr8.hexagonal.domain.rest.handler;

import com.algorithmxlr8.hexagonal.adapter.CreateProductPort;
import com.algorithmxlr8.hexagonal.application.model.Product;
import com.algorithmxlr8.hexagonal.domain.rest.dto.ProductRequest;
import com.algorithmxlr8.hexagonal.domain.rest.dto.ProductResponse;
import com.algorithmxlr8.hexagonal.domain.rest.mapper.ProductWebMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductHandler {

    private final CreateProductPort createProductPort;
    private final ProductWebMapper mapper;

    public ProductHandler(CreateProductPort createProductPort, ProductWebMapper mapper) {
        this.createProductPort = createProductPort;
        this.mapper = mapper;
    }

    public ProductResponse create(ProductRequest request) {
        Product product = createProductPort.createProduct(mapper.toDomain(request));
        return mapper.toResponse(product);
    }
}
