package com.algorithmxlr8.hexagonal.domain.web.dto;

import java.math.BigDecimal;

public record ProductResponse(Long id, String name, String description, BigDecimal price, Integer quantity) {
}
