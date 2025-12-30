```markdown
Healthcare Alert System

A real-time healthcare alert system built using Spring Boot microservices, Python ML service, MySQL, Docker, Kafka, and RabbitMQ.
This project demonstrates event-driven architecture, ML integration, and containerization. Currently implemented services include data ingestion, analytics, Python ML service, and notifications.

Note: JWT/authentication is not included yet. Kafka and RabbitMQ are set up but minimal for now.

Project Status

API Gateway: routes requests

Device Ingestion Service: receives patient vitals

Analytics Service: consumes data from Kafka and calls ML service

Python ML Service: predicts patient risk using pre-trained models

Notification Service: simulates alerts using RabbitMQ

MySQL database for storing patient and risk records

Docker Compose ready for all services

Kafka & RabbitMQ containers included (basic setup)

Folder Structure
healthcare-alert-system/
│
├── docker-compose.yml
├── README.md
│
├── api-gateway/
│   ├── Dockerfile
│   └── src/main/java/com/healthcare/gateway/ApiGatewayApplication.java
│
├── device-ingestion-service/
│   ├── Dockerfile
│   └── src/main/java/com/healthcare/ingestion/
│       ├── controller/
│       ├── entity/
│       └── repository/
│
├── analytics-service/
│   ├── Dockerfile
│   └── src/main/java/com/healthcare/analytics/
│       ├── service/
│       ├── entity/
│       └── repository/
│
├── notification-service/
│   ├── Dockerfile
│   └── src/main/java/com/healthcare/notification/
│
├── python-ml-service/
│   ├── Dockerfile
│   ├── ml_service.py
│   ├── requirements.txt
│   └── ml-models/
│       ├── logistic_model_v1.pkl
│       └── lstm_model_v1.h5
│
├── database/
│   └── mysql/init.sql
│
├── kafka/
└── rabbitmq/

How It Works

Client sends patient vitals JSON to the API Gateway.

API Gateway routes to Device Ingestion Service.

Device Ingestion Service publishes the data to Kafka topic patient-vital-stream.

Analytics Service consumes messages from Kafka, calls Python ML Service to get risk score, and stores results in MySQL.

If risk is high (>= 0.8), Analytics Service publishes alert messages to RabbitMQ queue critical-alerts.

Notification Service consumes alerts and prints a simulated notification.

Example JSON Payload
{
"patientId": "p1",
"heartRate": 120,
"spo2": 88,
"bp_systolic": 150,
"bp_diastolic": 95,
"respiratoryRate": 30
}

How to Run

Place a trained model in python-ml-service/ml-models/logistic_model.pkl (optional; dummy model used if missing).

From the project root, run:

docker-compose up --build


⚠️ Requirements: Docker, Docker Compose, Java 17, Maven, Python 3.10

Notes

Each service runs in its own Docker container

Kafka handles streaming of patient vitals

RabbitMQ handles asynchronous alert notifications

Python ML service can be updated independently

Project is educational and not for real medical use

Future Enhancements

Add authentication (JWT/OAuth)

End-to-end integration testing

Real-time dashboards for vitals and risk scores

Model retraining and versioning

Prometheus/Grafana monitoring

Author

Sharanu Malipatil
AI & ML Engineering Student
Microservices | ML Systems | Event-Driven Architecture
```
