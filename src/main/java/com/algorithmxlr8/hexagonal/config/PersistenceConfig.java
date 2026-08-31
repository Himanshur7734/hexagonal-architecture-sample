package com.algorithmxlr8.hexagonal.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Explicitly declares where JPA entities and repositories live instead of
 * relying on default component scanning to find them. Makes the driven
 * adapter's persistence wiring visible in one place.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.algorithmxlr8.hexagonal.adapter.out.persistence")
@EntityScan(basePackages = "com.algorithmxlr8.hexagonal.adapter.out.persistence")
public class PersistenceConfig {
}
