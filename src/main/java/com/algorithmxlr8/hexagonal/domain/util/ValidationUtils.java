package com.algorithmxlr8.hexagonal.domain.util;

import java.math.BigDecimal;

/**
 * Generic, framework-free guard clauses. Kept separate from the domain
 * model so Product only holds business rules, not raw null/blank checks.
 */
public final class ValidationUtils {

    private ValidationUtils() {
    }

    public static String requireNonBlank(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " must not be blank");
        }
        return value;
    }

    public static BigDecimal requireNonNegative(BigDecimal value, String field) {
        if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(field + " must not be negative");
        }
        return value;
    }

    public static Integer requireNonNegative(Integer value, String field) {
        if (value == null || value < 0) {
            throw new IllegalArgumentException(field + " must not be negative");
        }
        return value;
    }
}
