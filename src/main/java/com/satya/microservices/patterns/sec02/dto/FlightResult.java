package com.satya.microservices.patterns.sec02.dto;


import java.time.LocalDate;

public record FlightResult(String airline,
                           String from,
                           String to,
                           Double price,
                           LocalDate date) {

    @Override
    public String toString() {
        return "FlightResult{" +
                "airline='" + airline + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", price=" + price +
                ", date=" + date +
                '}';
    }

}
