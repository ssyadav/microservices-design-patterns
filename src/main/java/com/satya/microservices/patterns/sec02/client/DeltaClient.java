package com.satya.microservices.patterns.sec02.client;

import com.satya.microservices.patterns.sec01.dto.ProductResponse;
import com.satya.microservices.patterns.sec02.dto.FlightResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DeltaClient {
    private final WebClient webClient;

    public DeltaClient(@Value("${sec02.delta.service.url}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Flux<FlightResult> getFlights(String from, String to) {
        return this.webClient.get()
                .uri("{from}/{to}", from, to)
                .retrieve()
                .bodyToFlux(FlightResult.class)
                .onErrorResume(e -> Mono.empty());
    }
}
