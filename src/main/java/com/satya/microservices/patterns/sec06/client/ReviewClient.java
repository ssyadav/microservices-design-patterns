package com.satya.microservices.patterns.sec06.client;

import com.satya.microservices.patterns.sec06.dto.Review;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ReviewClient {
  private final WebClient webClient;

  public ReviewClient(@Value("${sec06.review.service.url}") String baseUrl) {
    this.webClient = WebClient.builder().baseUrl(baseUrl).build();
  }

  public Mono<List<Review>> getReviews(Integer productId) {
    return this.webClient
        .get()
        .uri("/{id}", productId)
        .retrieve()
        .bodyToFlux(Review.class)
        .collectList()
        .timeout(Duration.ofMillis(500))
        .onErrorReturn(Collections.emptyList());
  }
}
