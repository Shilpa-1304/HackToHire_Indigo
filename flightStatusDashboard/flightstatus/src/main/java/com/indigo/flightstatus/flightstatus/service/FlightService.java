package com.indigo.flightstatus.flightstatus.service;

import com.indigo.flightstatus.flightstatus.model.Flight;

import java.util.List;

public interface FlightService {
    List<Flight> getAllFlights();

    Flight getFlight(String flight);
}
