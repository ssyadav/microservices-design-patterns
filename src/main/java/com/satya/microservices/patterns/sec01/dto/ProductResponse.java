package com.satya.microservices.patterns.sec01.dto;


public record ProductResponse(Integer id,
                              String category,
                              String description,
                              Integer price) {

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
