package com.algorithmxlr8.hexagonal.domain.rest.dto;

import java.math.BigDecimal;

public record ProductResponse(Long id, String name, String description, BigDecimal price, Integer quantity) {
}
