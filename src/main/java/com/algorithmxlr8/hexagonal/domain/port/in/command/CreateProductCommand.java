package com.algorithmxlr8.hexagonal.domain.port.in.command;

import java.math.BigDecimal;

public record CreateProductCommand(String name, String description, BigDecimal price, Integer quantity) {
}
