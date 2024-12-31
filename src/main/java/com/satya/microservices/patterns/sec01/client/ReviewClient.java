package com.satya.microservices.patterns.sec01.client;

import com.satya.microservices.patterns.sec01.dto.Review;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Service
public class ReviewClient {
  private final WebClient webClient;

  public ReviewClient(@Value("${sec01.review.service.url}") String baseUrl) {
    this.webClient = WebClient.builder().baseUrl(baseUrl).build();
  }

  public Mono<List<Review>> getReviews(Integer productId) {
    return this.webClient
        .get()
        .uri("/{id}", productId)
        .retrieve()
        .bodyToFlux(Review.class)
        .collectList()
        .onErrorReturn(Collections.emptyList());
  }
}
