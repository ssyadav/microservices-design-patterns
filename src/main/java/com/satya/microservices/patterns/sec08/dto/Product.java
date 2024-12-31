package com.satya.microservices.patterns.sec08.dto;


public record Product(Integer id,
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
