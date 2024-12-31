package com.satya.microservices.patterns.sec02.client;

import com.satya.microservices.patterns.sec02.dto.FlightResult;
import com.satya.microservices.patterns.sec02.dto.FrontierRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FrontierClient {
  private final WebClient webClient;

  public FrontierClient(@Value("${sec02.frontier.service.url}") String baseUrl) {
    this.webClient = WebClient.builder().baseUrl(baseUrl).build();
  }

  public Flux<FlightResult> getFlights(String from, String to) {
    var request = new FrontierRequest(from, to);
    return this.webClient
        .post()
        .bodyValue(request)
        .retrieve()
        .bodyToFlux(FlightResult.class)
        .onErrorResume(e -> Mono.empty());
  }
}
