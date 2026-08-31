package com.hexagonal.domain.config;

import com.hexagonal.application.mapper.ProductPersistenceMapper;
import com.hexagonal.domain.rest.mapper.ProductWebMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ProductWebMapper productWebMapper() {
        return new ProductWebMapper();
    }

    @Bean
    public ProductPersistenceMapper productPersistenceMapper() {
        return new ProductPersistenceMapper();
    }
}
