package com.satya.microservices.patterns.sec09.client;

import com.satya.microservices.patterns.sec09.dto.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductClient {

    private final WebClient webClient;

    public ProductClient(@Value("${sec09.product.service.url}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<Product> getProduct(Integer productId) {
        return this.webClient.get()
                .uri("{id}", productId)
                .retrieve()
                .bodyToMono(Product.class)
                //.timeout(Duration.ofMillis(500))
                .onErrorResume(e -> Mono.empty());
    }
}