package com.satya.microservices.patterns.sec08.dto;


public record Review(Integer id,
                     String user,
                     Integer rating,
                     String comment) {

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}
