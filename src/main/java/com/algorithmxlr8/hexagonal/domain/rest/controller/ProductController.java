package com.algorithmxlr8.hexagonal.domain.rest.controller;

import com.algorithmxlr8.hexagonal.adapter.CreateProductUseCase;
import com.algorithmxlr8.hexagonal.application.model.Product;
import com.algorithmxlr8.hexagonal.domain.rest.dto.ProductRequest;
import com.algorithmxlr8.hexagonal.domain.rest.dto.ProductResponse;
import com.algorithmxlr8.hexagonal.domain.rest.mapper.ProductWebMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final ProductWebMapper mapper;

    public ProductController(CreateProductUseCase createProductUseCase, ProductWebMapper mapper) {
        this.createProductUseCase = createProductUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
        Product product = createProductUseCase.createProduct(mapper.toDomain(request));
        return ResponseEntity.created(URI.create("/api/products/" + product.getId()))
                .body(mapper.toResponse(product));
    }
}
