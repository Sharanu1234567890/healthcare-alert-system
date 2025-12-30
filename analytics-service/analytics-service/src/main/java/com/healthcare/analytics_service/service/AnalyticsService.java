package com.healthcare.analytics_service.service;

import com.healthcare.analytics_service.entity.AuditLog;
import com.healthcare.analytics_service.entity.RiskResult;
import com.healthcare.analytics_service.repository.AuditLogRepository;
import com.healthcare.analytics_service.repository.RiskResultRepository;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class AnalyticsService {

    private final RiskResultRepository riskRepo;
    private final AuditLogRepository auditRepo;
    private final RabbitTemplate rabbitTemplate;
    private final RestTemplate restTemplate = new RestTemplate();

    public AnalyticsService(RiskResultRepository riskRepo, AuditLogRepository auditRepo, RabbitTemplate rabbitTemplate) {
        this.riskRepo = riskRepo;
        this.auditRepo = auditRepo;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void processVital(Map<String, Object> vital) {
        String patientId = (String) vital.get("patientId");
        Double risk = restTemplate.postForObject("http://python-ml-service:5000/predict", vital, Map.class)
                .get("risk_score") instanceof Double ?
                (Double) restTemplate.postForObject("http://python-ml-service:5000/predict", vital, Map.class).get("risk_score") : 0.0;

        RiskResult rr = new RiskResult();
        rr.setPatientId(patientId);
        rr.setRiskScore(risk);
        riskRepo.save(rr);

        AuditLog log = new AuditLog();
        log.setPatientId(patientId);
        log.setEventType("ML_PREDICTION");
        log.setDetails("Risk score: " + risk);
        auditRepo.save(log);

        if (risk > 0.7) {
            rabbitTemplate.convertAndSend("critical-alerts", "Patient " + patientId + " risk score high: " + risk);
        }
    }
}
