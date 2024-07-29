package com.indigo.flightstatus.flightstatus.controller;

import com.indigo.flightstatus.flightstatus.model.Flight;
import com.indigo.flightstatus.flightstatus.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getFlightsStatus")
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getFlight")
    public Flight getFlight(@RequestParam String flight){
        return flightService.getFlight(flight);
    }
}

