package com.indigo.flightstatus.flightstatus.repository;

import com.indigo.flightstatus.flightstatus.model.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends MongoRepository<Flight, String> {
    Flight findByFlightNumber(String flightNumber);
}
