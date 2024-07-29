package com.indigo.flightstatus.flightstatus.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class GateChangeConsumer {
    @KafkaListener(topics = "flight-status", groupId = "flightsGroup")
    public void consume(String message) {
        sendSmsNotification(message);
        sendEmailNotification(message);
        sendAppNotification(message);
    }

    private void sendSmsNotification(String message) {
        System.out.println("SMS Notification: " + message);
    }

    private void sendEmailNotification(String message) {
        System.out.println("Email Notification: " + message);
    }

    private void sendAppNotification(String message) {
        System.out.println("App Notification: " + message);
    }
}