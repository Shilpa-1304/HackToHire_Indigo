package com.indigo.flightstatus.flightstatus.service;

import com.indigo.flightstatus.flightstatus.model.Flight;
import com.indigo.flightstatus.flightstatus.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService{
    @Autowired
    FlightRepository repo;
    @Autowired
    private GateChangeProducer gateChangeProducer;
    @Override
    public List<Flight> getAllFlights() {
        System.out.println("Reached at implementation.");
        return repo.findAll();
    }
    @Override
    public Flight getFlight(String flight){
        return repo.findByFlightNumber(flight);
    }
    public void updateGateChange(Flight flight, String newGate) {
        // @shilpa, July 29,Updating gate in the flight object
        flight.getGateChange().setNewGate(newGate);
        repo.save(flight);

        //@shilpa, July 29, Publishing status change event to Kafka server
        String message = "Flight " + flight.getFlightNumber() + " gate changed to " + newGate;
        gateChangeProducer.publishStatusChange(message);
    }

}
