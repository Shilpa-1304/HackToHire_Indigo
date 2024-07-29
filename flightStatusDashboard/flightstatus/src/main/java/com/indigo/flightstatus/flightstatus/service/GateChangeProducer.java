package com.indigo.flightstatus.flightstatus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class GateChangeProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "flight-status";

    public void publishStatusChange(String message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
