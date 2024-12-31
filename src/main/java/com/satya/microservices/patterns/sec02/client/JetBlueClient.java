package com.satya.microservices.patterns.sec02.client;

import com.satya.microservices.patterns.sec02.dto.FlightResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class JetBlueClient {

  public static final String JETBLUE = "JETBLUE";
  private static final Logger log = LoggerFactory.getLogger(JetBlueClient.class);
  private final WebClient webClient;

  public JetBlueClient(@Value("${sec02.jetblue.service.url}") String baseUrl) {
    this.webClient = WebClient.builder().baseUrl(baseUrl).build();
  }

  public Flux<FlightResult> getFlights(String from, String to) {
    return this.webClient
        .get()
        .uri("{from}/{to}", from, to)
        .retrieve()
        .bodyToFlux(FlightResult.class)
        .map(fr -> new FlightResult("JETBLUE", from, to, fr.price(), fr.date()))
        .onErrorResume(e -> Mono.empty());
  }
}
