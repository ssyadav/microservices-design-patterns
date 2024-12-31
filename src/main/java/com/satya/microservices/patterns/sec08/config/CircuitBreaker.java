package com.satya.microservices.patterns.sec08.config;

import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CircuitBreaker {

    @Bean
    public CircuitBreakerConfigCustomizer reviewService(){
        return CircuitBreakerConfigCustomizer.of("review-service", builder ->
                builder.minimumNumberOfCalls(4));
    }
}
