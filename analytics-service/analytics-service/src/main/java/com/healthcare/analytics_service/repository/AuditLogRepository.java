package com.healthcare.analytics_service.repository;

import com.healthcare.analytics_service.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {}
