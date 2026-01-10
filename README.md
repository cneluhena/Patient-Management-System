# Patient Management System

A comprehensive web-based patient management system built with Java Spring Boot and modern web technologies utilizing **microservices architecture**. This system provides healthcare facilities with tools to manage patient records, appointments, medical history, and administrative tasks efficiently. The application is designed with a distributed architecture, ensuring scalability, maintainability, and independent service deployment.

## Architecture

This project follows a **microservices architecture** pattern, where different business capabilities are separated into independent, loosely-coupled services that communicate with each other.

## Services

### 1. Auth Service
Contains configurations for the authentication and authorization server. **Keycloak** is used as the Identity Management Server, providing robust OAuth 2.0 and OpenID Connect protocols for securing the microservices.

### 2. Doctor Service
Handles all doctor-related operations including doctor profiles, specializations, availability management, and doctor information retrieval.

### 3. Patient Service
Manages patient-related services including patient registration, profile management, medical history, and patient information queries.

### 4. Appointment Service
Responsible for patient appointment-related tasks such as scheduling, updating, canceling appointments, and managing appointment statuses between patients and doctors.

### 5. Service Discovery
Implements the Eureka Server for service registration and discovery, enabling microservices to locate and communicate with each other dynamically.

### 6. API Gateway
Acts as a single entry point for all client requests, routing them to appropriate microservices. Handles cross-cutting concerns like authentication, logging, and load balancing.

## Technology Stack

### Backend
- Java 21+
- Spring Boot 3.4.12
- Spring Cloud (Eureka, Gateway)
- Spring Data JPA
- Spring Security with OAuth 2.0
- Keycloak (Identity Management)
- PostgreSQL Database
- Apache Kafka (Event Streaming)
- Maven

### Infrastructure
- Docker & Docker Compose
- Eureka Server (Service Discovery)
- Apache Kafka (Message Broker)

## Prerequisites

Before running this application, ensure you have the following installed:

- Java Development Kit (JDK) 21
- Docker & Docker Compose
- PostgreSQL 17+

## Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/cneluhena/Patient-Management-System.git
cd Patient-Management-System
```

### 2. Setup Environment Variables

#### Auth Service Environment Variables

Create a `.env` file inside the `auth-service` directory:

```env
# Keycloak Configuration
KC_DB_URL=jdbc:postgresql://host.docker.internal:5432/keycloak
KC_DB_USERNAME=postgres
KC_DB_PASSWORD=postgres
KC_BOOTSTRAP_ADMIN_USERNAME=admin
KC_BOOTSTRAP_ADMIN_PASSWORD=admin
```

#### Service Environment Variables

Create a `.env` file inside the every service directory:

```env
# Database Configuration
DB_USERNAME=postgres
DB_PASSWORD=postgres
DB_URL=jdbc:postgresql://localhost:5432/{dbname}
```

**Important**: Make sure to add these variables as environment variables in each respective service directory.

### 3. Start Keycloak Server

Navigate to the `auth-service` directory and start Keycloak using Docker Compose:

```bash
cd auth-service
docker-compose up -d
```

Keycloak will be available at `http://localhost:8080`

- **Admin Console**: `http://localhost:8443`
- **Username**: admin (or value from `KC_BOOTSTRAP_ADMIN_USERNAME`)
- **Password**: admin123 (or value from `KC_BOOTSTRAP_ADMIN_PASSWORD`)

### 4. Start Kafka Server

Return to the root directory and start Kafka using Docker Compose:

```bash
cd ..
docker-compose up -d
```

This will start Kafka .

### 5. Start Microservices

Start each microservice individually by navigating to its directory and running:

#### Service Discovery (Eureka Server)
```bash
cd service-discovery
mvn spring-boot:run
```

Wait for Eureka Server to start completely (available at `http://localhost:8761`)

#### Auth Service
```bash
cd auth-service
mvn spring-boot:run
```

#### Doctor Service
```bash
cd doctor-service
mvn spring-boot:run
```

#### Patient Service
```bash
cd patient-service
mvn spring-boot:run
```

#### Appointment Service
```bash
cd appointment-service
mvn spring-boot:run
```

### 6. Verify Service Registration

Open the Eureka Dashboard at `http://localhost:8761` to verify that all services are registered successfully.

## Project Structure

```
Patient-Management-System/
├── auth-service/              # Authentication & Authorization
│   ├── docker-compose.yml     # Keycloak setup
│   ├── .env                   # Keycloak environment variables
│   └── src/
├── doctor-service/            # Doctor management
│   ├── .env                   # Doctor service database config
│   └── src/
├── patient-service/           # Patient management
│   ├── .env                   # Patient service database config
│   └── src/
├── appointment-service/       # Appointment scheduling
│   ├── .env                   # Appointment service database config
│   └── src/
├── service-discovery/         # Eureka Server
│   └── src/
├── docker-compose.yml         # Kafka & Zookeeper setup
└── README.md
```

## API Gateway

All microservices are accessed through the API Gateway:

- **Doctor Service**: `http://localhost:4004/api/doctors`
- **Patient Service**: `http://localhost:4004/api/patients`
- **Appointment Service**: `http://localhost:4004/api/appointments`

## Authentication

All API endpoints are secured using Keycloak OAuth 2.0. To access protected endpoints:

1. Obtain an access token from Keycloak
2. Include the token in the Authorization header: `Bearer <token>`

## Event-Driven Communication

The system uses Apache Kafka for asynchronous communication between services:

- Appointment notifications
- Patient updates propagation
- Doctor availability changes

## Troubleshooting

### Services not registering with Eureka
- Ensure Eureka Server is running before starting other services
- Check the `EUREKA_SERVER_URL` environment variable

### Keycloak connection issues
- Verify Keycloak container is running: `docker ps`
- Check Keycloak logs: `docker logs <keycloak-container-id>`

To stop individual microservices, use `Ctrl+C` in their respective terminal windows.

## Contact

**Developer**: cneluhena

**GitHub**: [https://github.com/cneluhena](https://github.com/cneluhena)

**Project Link**: [https://github.com/cneluhena/Patient-Management-System](https://github.com/cneluhena/Patient-Management-System)

