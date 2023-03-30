package com.animals.configuration;

import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class FeignCatConfig {

    @Value("${client.cat.apiKey}")
    private String apiKey;

    @Bean
    public RequestInterceptor requestCatInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header("x-api-key", apiKey);
        };
    }

}
