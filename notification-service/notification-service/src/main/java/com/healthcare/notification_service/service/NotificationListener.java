package com.healthcare.notification_service.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class    NotificationListener {

    @RabbitListener(queues = "critical-alerts")
    public void receiveAlert(String message) {
        System.out.println("ALERT: " + message);
        // Here later we can integrate SMS / Email
    }
}
