```markdown
# healthcare-alert-system (simple starter)

Run locally:
1. Put a trained model at python-ml-service/ml-models/logistic_model.pkl (optional; a tiny dummy is created if missing).
2. From repository root:
   docker-compose up --build

Endpoints:
- POST http://localhost:8080/ingest/vitals (use header X-API-KEY: secret-key)
  Body example:
  {
    "patientId": "p1",
    "heartRate": 120,
    "spo2": 88,
    "bp_systolic": 150,
    "bp_diastolic": 95,
    "respiratoryRate": 30
  }

What happens:
- API Gateway forwards to device-ingestion-service which publishes the payload to Kafka topic patient-vital-stream.
- analytics-service consumes Kafka message, calls python-ml-service via Feign (/predict), stores a RiskRecord in Postgres, and if risk >= 0.8 publishes a message on RabbitMQ queue critical-alerts.
- notification-service consumes critical-alerts and prints a simulated notification.
```