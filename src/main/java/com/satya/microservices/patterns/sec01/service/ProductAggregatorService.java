package com.satya.microservices.patterns.sec01.service;

import com.satya.microservices.patterns.sec01.client.ProductClient;
import com.satya.microservices.patterns.sec01.client.PromotionClient;
import com.satya.microservices.patterns.sec01.client.ReviewClient;
import com.satya.microservices.patterns.sec01.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProductAggregatorService {

  @Autowired private ProductClient productClient;
  @Autowired private PromotionClient promotionClient;
  @Autowired private ReviewClient reviewClient;

  public Mono<ProductAggregate> aggregate(int productId) {
    return Mono.zip(
            this.productClient.getProduct(productId),
            this.promotionClient.getPromotion(productId),
            this.reviewClient.getReviews(productId))
        .map(tuple -> toDto(tuple.getT1(), tuple.getT2(), tuple.getT3()));
  }

  private ProductAggregate toDto(
      ProductResponse product, PromotionResponse promotion, List<Review> reviews) {
    var amountSaved = product.price() * promotion.discount() / 100;
    var discountedPrice = product.price() - amountSaved;
    var price =
        new Price(
            product.price(),
            promotion.discount(),
            discountedPrice,
            amountSaved,
            promotion.endDate());

    return new ProductAggregate(
        product.id(), product.category(), product.description(), price, reviews);
  }
}
