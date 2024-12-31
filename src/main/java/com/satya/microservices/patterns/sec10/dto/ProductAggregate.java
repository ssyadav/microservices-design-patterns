package com.satya.microservices.patterns.sec10.dto;

import java.util.List;

public record ProductAggregate(Integer id,
                               String category,
                               String description,
                               List<Review> reviews) {}
