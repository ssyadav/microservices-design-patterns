package com.satya.microservices.patterns.sec08.dto;

import java.util.List;

public record ProductAggregate(Integer id,
                               String category,
                               String description,
                               List<Review> reviews) {}
