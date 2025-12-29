package com.healthcare.analytics_service.repo;

//import com.healthcare.analytics.model.RiskRecord;
import com.healthcare.analytics_service.model.RiskRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiskRecordRepository extends JpaRepository<RiskRecord, Long> {}