package com.satya.microservices.patterns.sec08.client;

import com.satya.microservices.patterns.sec08.dto.Review;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ReviewClient {
  private final WebClient webClient;

  public ReviewClient(@Value("${sec08.review.service.url}") String baseUrl) {
    this.webClient = WebClient.builder().baseUrl(baseUrl).build();
  }

  @CircuitBreaker(name = "review-service", fallbackMethod = "fallBackReview")
  public Mono<List<Review>> getReviews(Integer productId) {
    return this.webClient
        .get()
        .uri("/{id}", productId)
        .retrieve()
        .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
        .bodyToFlux(Review.class)
        .collectList()
        .retry(5)
        .timeout(Duration.ofMillis(300))
        .onErrorReturn(Collections.emptyList());
  }

  public Mono<List<Review>> fallBackReview(Integer id, Throwable ex){
    System.out.println("fallback reviews called : " + ex.getMessage());
    return Mono.just(Collections.emptyList());
  }
}
