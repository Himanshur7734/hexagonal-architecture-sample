package com.hexagonal.domain.rest.controller;

import com.hexagonal.domain.rest.dto.ProductRequest;
import com.hexagonal.domain.rest.dto.ProductResponse;
import com.hexagonal.domain.rest.handler.ProductHandler;
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

    private final ProductHandler productHandler;

    public ProductController(ProductHandler productHandler) {
        this.productHandler = productHandler;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
        ProductResponse response = productHandler.create(request);
        return ResponseEntity.created(URI.create("/api/products/" + response.getId())).body(response);
    }
}
