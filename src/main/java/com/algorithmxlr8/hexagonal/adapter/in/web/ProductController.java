package com.algorithmxlr8.hexagonal.adapter.in.web;

import com.algorithmxlr8.hexagonal.adapter.in.web.dto.ProductRequest;
import com.algorithmxlr8.hexagonal.adapter.in.web.dto.ProductResponse;
import com.algorithmxlr8.hexagonal.domain.model.Product;
import com.algorithmxlr8.hexagonal.domain.port.in.CreateProductCommand;
import com.algorithmxlr8.hexagonal.domain.port.in.CreateProductUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * Driving adapter. Depends only on the inbound port (use case), never
 * on ProductService directly, so the web layer stays swappable.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;

    public ProductController(CreateProductUseCase createProductUseCase) {
        this.createProductUseCase = createProductUseCase;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
        Product product = createProductUseCase.createProduct(
                new CreateProductCommand(request.name(), request.description(), request.price(), request.quantity()));
        return ResponseEntity.created(URI.create("/api/products/" + product.getId()))
                .body(ProductResponse.from(product));
    }
}
