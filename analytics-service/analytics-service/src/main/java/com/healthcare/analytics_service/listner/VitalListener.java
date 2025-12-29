package com.healthcare.analytics_service.listner;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.healthcare.analytics_service.client.MLFeignClient;
import com.healthcare.analytics_service.model.RiskRecord;
import com.healthcare.analytics_service.repo.RiskRecordRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Map;

@Component
public class VitalListener {

    private final ObjectMapper mapper = new ObjectMapper();
    private final MLFeignClient ml;
    private final RiskRecordRepository repo;
    private final RabbitTemplate rabbit;
    private final double threshold;

    public VitalListener(MLFeignClient ml, RiskRecordRepository repo, RabbitTemplate rabbit,
                         @Value("${app.risk.threshold:0.8}") double threshold) {
        this.ml = ml;
        this.repo = repo;
        this.rabbit = rabbit;
        this.threshold = threshold;
    }

    @KafkaListener(topics = "${app.kafka.topic:patient-vital-stream}", groupId = "analytics")
    public void listen(String msg) {
        try {
            Map<String, Object> vitals = mapper.readValue(msg, Map.class);
            Map<String, Object> res = ml.predict(vitals);
            Double score = res == null ? 0.0 : Double.valueOf(res.getOrDefault("risk_score", 0.0).toString());

            RiskRecord r = new RiskRecord();
            r.setPatientId(vitals.getOrDefault("patientId", "unknown").toString());
            r.setRiskScore(score);
            r.setRaw(msg);
            r.setCreatedAt(OffsetDateTime.now());
            repo.save(r);

            if (score >= threshold) {
                rabbit.convertAndSend("", "critical-alerts", Map.of(
                        "patientId", r.getPatientId(),
                        "riskScore", r.getRiskScore(),
                        "id", r.getId()
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}