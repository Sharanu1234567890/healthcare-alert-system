CREATE DATABASE IF NOT EXISTS healthcare_db;

USE healthcare_db;

-- Patient table
CREATE TABLE IF NOT EXISTS patients (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_id VARCHAR(50) UNIQUE,
    name VARCHAR(100),
    age INT,
    device_id VARCHAR(50)
);

-- Risk results table
CREATE TABLE IF NOT EXISTS risk_results (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_id VARCHAR(50),
    risk_score DOUBLE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Audit logs table
CREATE TABLE IF NOT EXISTS audit_logs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_id VARCHAR(50),
    event_type VARCHAR(50),
    details TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
