package com.healthcare.notification_service.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AlertListener {

    @RabbitListener(queues = "critical-alerts")
    public void onAlert(Map<String, Object> alert) {
        // simple notification simulation
        System.out.println("NOTIFICATION -> send to doctor: " + alert);
    }
}