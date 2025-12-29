package com.healthcare.analytics_service.model;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
public class RiskRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientId;
    private Double riskScore;

    @Lob
    private String raw;

    private OffsetDateTime createdAt;

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public String getPatientId(){ return patientId; }
    public void setPatientId(String p){ this.patientId = p; }
    public Double getRiskScore(){ return riskScore; }
    public void setRiskScore(Double r){ this.riskScore = r; }
    public String getRaw(){ return raw; }
    public void setRaw(String r){ this.raw = r; }
    public OffsetDateTime getCreatedAt(){ return createdAt; }
    public void setCreatedAt(OffsetDateTime t){ this.createdAt = t; }
}