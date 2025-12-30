package com.healthcare.device_ingestion_service.controller;

import com.healthcare.device_ingestion_service.entity.PatientVital;
import com.healthcare.device_ingestion_service.repository.PatientVitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/vitals")
public class VitalController {

    @Autowired
    private PatientVitalRepository repository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping
    public ResponseEntity<?> receiveVital(@RequestBody Map<String, Object> payload) {
        PatientVital vital = new PatientVital();
        vital.setPatientId((String) payload.get("patientId"));
        vital.setHeartRate((Integer) payload.get("heartRate"));
        vital.setSpo2((Integer) payload.get("spo2"));
        vital.setBp((Integer) payload.get("bp"));
        repository.save(vital);

        kafkaTemplate.send("patient-vital-stream", payload.get("patientId").toString(), payload.toString());
        return ResponseEntity.ok("Received");
    }
}
