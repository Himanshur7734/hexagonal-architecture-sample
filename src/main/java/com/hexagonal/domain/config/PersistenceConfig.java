package com.hexagonal.domain.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.hexagonal.application.repositories")
@EntityScan(basePackages = "com.hexagonal.application.entity")
public class PersistenceConfig {
}
