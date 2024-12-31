package com.satya.microservices.patterns.sec07.controller;

import com.satya.microservices.patterns.sec07.dto.ProductAggregate;
import com.satya.microservices.patterns.sec07.service.ProductAggregatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/sec07")
public class AggregatorController {

  public static final Logger LOGGER = LoggerFactory.getLogger(AggregatorController.class);

  @Autowired private ProductAggregatorService aggregatorService;

  @GetMapping("/product/{id}")
  public Mono<ResponseEntity<ProductAggregate>> getProductAggregate(@PathVariable Integer id) {
    LOGGER.info("Received request to get product details for id: {}", id);
    return this.aggregatorService
        .aggregate(id)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }
}
