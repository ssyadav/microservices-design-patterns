package com.satya.microservices.patterns.sec06.service;

import com.satya.microservices.patterns.sec06.client.ProductClient;
import com.satya.microservices.patterns.sec06.client.ReviewClient;
import com.satya.microservices.patterns.sec06.dto.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProductAggregatorService {

  @Autowired private ProductClient productClient;
  @Autowired private ReviewClient reviewClient;

  public Mono<ProductAggregate> aggregate(int productId) {
    return Mono.zip(
            this.productClient.getProduct(productId),
            this.reviewClient.getReviews(productId))
        .map(tuple -> toDto(tuple.getT1(), tuple.getT2()));
  }

  private ProductAggregate toDto(
          Product product, List<Review> reviews) {
    return new ProductAggregate(
        product.id(), product.category(), product.description(), reviews);
  }
}
