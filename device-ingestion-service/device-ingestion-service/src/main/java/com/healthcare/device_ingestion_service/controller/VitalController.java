package com.healthcare.device_ingestion_service.controller;

//package com.healthcare.ingestion.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingest")
public class VitalController {

    private final KafkaTemplate<String, String> kafka;
    private final String topic;
    private final ObjectMapper mapper = new ObjectMapper();

    public VitalController(KafkaTemplate<String, String> kafka,
                           @Value("${app.kafka.topic:patient-vital-stream}") String topic) {
        this.kafka = kafka;
        this.topic = topic;
    }

    @PostMapping("/vitals")
    public ResponseEntity<?> postVitals(@RequestBody Map<String, Object> payload) throws Exception {
        String key = payload.getOrDefault("patientId", "unknown").toString();
        String json = mapper.writeValueAsString(payload);
        kafka.send(topic, key, json);
        return ResponseEntity.accepted().body(Map.of("status", "sent"));
    }
}