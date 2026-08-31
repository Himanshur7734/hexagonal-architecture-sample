package com.hexagonal.domain.rest.handler;

import com.hexagonal.adapter.CreateProductPort;
import com.hexagonal.application.model.Product;
import com.hexagonal.domain.rest.dto.ProductRequest;
import com.hexagonal.domain.rest.dto.ProductResponse;
import com.hexagonal.domain.service.mapper.ProductWebMapper;

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
