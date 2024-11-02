package com.khanhisdev.orderservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title("API-order-service")
                .version("v1.0.0").description("description").license(new License().name("API license").url("http://domain")));


    }
}
