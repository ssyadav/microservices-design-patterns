package com.satya.microservices.patterns.sec01.dto;


import java.time.LocalDate;

public record PromotionResponse(Integer id,
                                String type,
                                Double discount,
                                LocalDate endDate) {

    @Override
    public String toString() {
        return "PromotionResponse{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", discount=" + discount +
                ", endDate=" + endDate +
                '}';
    }
}
