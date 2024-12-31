package com.satya.microservices.patterns.sec01.client;

import com.satya.microservices.patterns.sec01.dto.PromotionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class PromotionClient {

  private final WebClient webClient;

  private final PromotionResponse noPromotion;

  public PromotionClient(@Value("${sec01.promotion.service.url}") String baseUrl) {
    this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    this.noPromotion = new PromotionResponse(-1, "no promotion", 0.0, LocalDate.now());
  }

  public Mono<PromotionResponse> getPromotion(Integer productId) {
    return this.webClient
        .get()
        .uri("/{id}", productId)
        .retrieve()
        .bodyToMono(PromotionResponse.class)
        .onErrorReturn(noPromotion);
  }
}
