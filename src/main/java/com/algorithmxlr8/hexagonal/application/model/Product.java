package com.algorithmxlr8.hexagonal.application.model;

import com.algorithmxlr8.hexagonal.domain.util.ValidationUtils;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Core domain entity. Deliberately framework-free: no JPA, no Spring,
 * no Jackson annotations. Business invariants are enforced in the
 * constructor, not left to adapters.
 */
public class Product {

    private final Long id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final Integer quantity;

    public Product(Long id, String name, String description, BigDecimal price, Integer quantity) {
        this.id = id;
        this.name = ValidationUtils.requireNonBlank(name, "name");
        this.description = description;
        this.price = ValidationUtils.requireNonNegative(price, "price");
        this.quantity = ValidationUtils.requireNonNegative(quantity, "quantity");
    }

    public static Product createNew(String name, String description, BigDecimal price, Integer quantity) {
        return new Product(null, name, description, price, quantity);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
