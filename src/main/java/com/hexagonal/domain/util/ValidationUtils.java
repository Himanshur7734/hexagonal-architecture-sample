package com.hexagonal.domain.util;

public final class ValidationUtils {

    private ValidationUtils() {
    }

    public static String requireNonBlank(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " must not be blank");
        }
        return value;
    }

    public static double requireNonNegative(double value, String field) {
        if (value < 0) {
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
