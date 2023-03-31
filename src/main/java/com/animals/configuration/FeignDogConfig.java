package com.animals.configuration;

import feign.RequestInterceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignDogConfig {

    @Value("${client.dog.apiKey}")
    private String apiKey;

    @Bean
    public RequestInterceptor requestDogInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header("x-api-key", apiKey);
        };
    }

}
