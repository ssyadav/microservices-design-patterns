package com.satya.microservices.patterns.sec01.dto;

import java.util.List;

public record ProductAggregate(Integer id,
                               String category,
                               String description,
                               Price price,
                               List<Review> reviews) {}
