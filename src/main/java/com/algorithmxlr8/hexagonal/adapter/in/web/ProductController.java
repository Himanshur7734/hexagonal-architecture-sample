package com.algorithmxlr8.hexagonal.adapter.in.web;

import com.algorithmxlr8.hexagonal.adapter.in.web.dto.ProductRequest;
import com.algorithmxlr8.hexagonal.adapter.in.web.dto.ProductResponse;
import com.algorithmxlr8.hexagonal.domain.model.Product;
import com.algorithmxlr8.hexagonal.domain.port.in.CreateProductCommand;
import com.algorithmxlr8.hexagonal.domain.port.in.CreateProductUseCase;
import com.algorithmxlr8.hexagonal.domain.port.in.GetProductUseCase;
import com.algorithmxlr8.hexagonal.domain.port.in.ListProductsUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

/**
 * Driving adapter. Depends only on the inbound ports (use cases), never
 * on ProductService directly, so the web layer stays swappable.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final GetProductUseCase getProductUseCase;
    private final ListProductsUseCase listProductsUseCase;

    public ProductController(CreateProductUseCase createProductUseCase,
                              GetProductUseCase getProductUseCase,
                              ListProductsUseCase listProductsUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.getProductUseCase = getProductUseCase;
        this.listProductsUseCase = listProductsUseCase;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
        Product product = createProductUseCase.createProduct(
                new CreateProductCommand(request.name(), request.description(), request.price(), request.quantity()));
        return ResponseEntity.created(URI.create("/api/products/" + product.getId()))
                .body(ProductResponse.from(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
        Product product = getProductUseCase.getProduct(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ProductResponse.from(product));
    }

    @GetMapping
    public List<ProductResponse> getAll() {
        return listProductsUseCase.listProducts().stream().map(ProductResponse::from).toList();
    }
}
