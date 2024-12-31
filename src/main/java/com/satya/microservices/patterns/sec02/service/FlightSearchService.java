package com.satya.microservices.patterns.sec02.service;

import com.satya.microservices.patterns.sec02.client.DeltaClient;
import com.satya.microservices.patterns.sec02.client.FrontierClient;
import com.satya.microservices.patterns.sec02.client.JetBlueClient;
import com.satya.microservices.patterns.sec02.dto.FlightResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
public class FlightSearchService {

  @Autowired private JetBlueClient jetBlueClient;
  @Autowired private DeltaClient deltaClient;
  @Autowired private FrontierClient frontierClient;

  public Flux<FlightResult> getFlights(String from, String to) {
    return Flux.merge(
            jetBlueClient.getFlights(from, to),
            deltaClient.getFlights(from, to),
            frontierClient.getFlights(from, to))
        .take(Duration.ofSeconds(3));
  }
}
