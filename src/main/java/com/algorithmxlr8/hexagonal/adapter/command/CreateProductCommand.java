package com.algorithmxlr8.hexagonal.adapter.command;

import java.math.BigDecimal;

public record CreateProductCommand(String name, String description, BigDecimal price, Integer quantity) {
}
