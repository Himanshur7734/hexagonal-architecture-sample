package com.algorithmxlr8.hexagonal.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Core domain entity. Deliberately framework-free: no JPA, no Spring,
 * no Jackson annotations. Business invariants are enforced in the
 * constructor and in updateDetails, not left to adapters.
 */
public class Product {

    private final Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;

    public Product(Long id, String name, String description, BigDecimal price, Integer quantity) {
        this.id = id;
        this.name = requireNonBlank(name, "name");
        this.description = description;
        this.price = requireNonNegative(price, "price");
        this.quantity = requireNonNegative(quantity, "quantity");
    }

    public static Product createNew(String name, String description, BigDecimal price, Integer quantity) {
        return new Product(null, name, description, price, quantity);
    }

    public void updateDetails(String name, String description, BigDecimal price, Integer quantity) {
        this.name = requireNonBlank(name, "name");
        this.description = description;
        this.price = requireNonNegative(price, "price");
        this.quantity = requireNonNegative(quantity, "quantity");
    }

    private static String requireNonBlank(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " must not be blank");
        }
        return value;
    }

    private static BigDecimal requireNonNegative(BigDecimal value, String field) {
        if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(field + " must not be negative");
        }
        return value;
    }

    private static Integer requireNonNegative(Integer value, String field) {
        if (value == null || value < 0) {
            throw new IllegalArgumentException(field + " must not be negative");
        }
        return value;
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
