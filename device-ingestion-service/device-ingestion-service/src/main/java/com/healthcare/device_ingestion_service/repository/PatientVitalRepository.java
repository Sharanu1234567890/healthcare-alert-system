package com.healthcare.device_ingestion_service.repository;

//import com.healthcare.ingestion.entity.PatientVital;
import com.healthcare.device_ingestion_service.entity.PatientVital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientVitalRepository extends JpaRepository<PatientVital, Long> {}
