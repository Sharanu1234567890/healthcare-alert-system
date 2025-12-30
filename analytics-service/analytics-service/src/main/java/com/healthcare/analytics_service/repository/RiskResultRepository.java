package com.healthcare.analytics_service.repository;
//import com.healthcare.analytics.entity.RiskResult;
import com.healthcare.analytics_service.entity.AuditLog;
import com.healthcare.analytics_service.entity.RiskResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiskResultRepository extends JpaRepository<RiskResult, Long> {}


