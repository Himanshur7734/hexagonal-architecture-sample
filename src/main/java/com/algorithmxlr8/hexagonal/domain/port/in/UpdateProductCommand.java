package com.algorithmxlr8.hexagonal.domain.port.in;

import java.math.BigDecimal;

public record UpdateProductCommand(String name, String description, BigDecimal price, Integer quantity) {
}
