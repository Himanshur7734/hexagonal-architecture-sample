package com.algorithmxlr8.hexagonal.domain.rest.controller;

import com.algorithmxlr8.hexagonal.domain.rest.dto.ProductRequest;
import com.algorithmxlr8.hexagonal.domain.rest.dto.ProductResponse;
import com.algorithmxlr8.hexagonal.domain.rest.facade.ProductFacade;
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

    private final ProductFacade productFacade;

    public ProductController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
        ProductResponse response = productFacade.create(request);
        return ResponseEntity.created(URI.create("/api/products/" + response.getId())).body(response);
    }
}
