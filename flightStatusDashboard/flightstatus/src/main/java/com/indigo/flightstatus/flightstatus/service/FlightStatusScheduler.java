package com.indigo.flightstatus.flightstatus.service;

import com.indigo.flightstatus.flightstatus.model.Flight;
import com.indigo.flightstatus.flightstatus.model.GateChange;
import com.indigo.flightstatus.flightstatus.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FlightStatusScheduler {
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightService flightService;

    @Autowired
    private GateChangeProducer gateChangeProducer;

    @Scheduled(fixedRate = 600000) // To fetch flight data in every 10 minutes
    public void checkFlightStatus() {
        try {
            List<Flight> flights = flightRepository.findAll();
            for (Flight flight : flights) {
                // @shilpa, July 29, To fetch latest flight status from airport database.
                Flight latestFlight = fetchLatestFlightStatus(flight.getFlightNumber());
                System.out.println(flight.getFlightNumber()+"<=="+flight.getStatus());
                System.out.println(latestFlight.getFlightNumber()+"<=="+latestFlight.getStatus());
                // Check if the gate has changed
                if(latestFlight.getFlightNumber()!=null){
                    if (!flight.getGateChange().getNewGate().equals(latestFlight.getGateChange().getNewGate())) {
                        // Updating the gate in the database
                        flight.getGateChange().setNewGate(latestFlight.getGateChange().getNewGate());

                        // Send notification using Kafka
                        String message = "Flight " + flight.getFlightNumber() + " gate changed to " + latestFlight.getGateChange().getNewGate();
                        gateChangeProducer.publishStatusChange(message);
                    }
                    if (latestFlight.isCancelled()==true) {
                        // To check cancellation status
                        flight.setCancelled(latestFlight.isCancelled());

                        // Send notification using Kafka
                        String message = "Flight " + flight.getFlightNumber() + " has been cancelled.";
                        gateChangeProducer.publishStatusChange(message);
                    }
                    if(!flight.getStatus().equals(latestFlight.getStatus())){
                        // To check status
                        flight.setStatus(latestFlight.getStatus());

                        // Send notification using Kafka
                        String message = "Flight status: " +  flight.getStatus();
                        gateChangeProducer.publishStatusChange(message);
                    }
                    flightRepository.save(flight);

                }
                else {
                    flightRepository.save(flight);
                }
            }
        }catch (Exception e){
            System.out.println("Exception: "+e);
        }

    }

    private Flight fetchLatestFlightStatus(String flightNumber) {
        /*
            @shilpa, July 29, An external API will be called to fetch latest flight status from airport databases for accurate
            information and all the properties of flight for eg. status, cancellation check ,Delays and gate change will be set
         */
        Flight flight = new Flight();
        GateChange gate=new GateChange();
        //@shilpa, July 29 ,As of now, To witness changes in flight status, Setting hard coded value
        if(Objects.equals(flightNumber, "AA123")){
            flight.setFlightNumber(flightNumber);
            gate.setOriginalGate("B12");
            gate.setNewGate("B13");
            System.out.println("Inside fetchLatestFlightStatus");
            flight.setCancelled(true);
            flight.setDelay("30 minutes");
            flight.setStatus("Cancelled");
            flight.setGateChange(gate);
        }
        return flight;
    }
}
