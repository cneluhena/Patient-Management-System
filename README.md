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

## Technology Stack

### Backend
- Java 17+
- Spring Boot 3.x
- Spring Cloud (Eureka, Gateway)
- Spring Data JPA
- Spring Security with OAuth 2.0
- Keycloak (Identity Management)
- MySQL Database
- Apache Kafka (Event Streaming)
- Maven

### Infrastructure
- Docker & Docker Compose
- Eureka Server (Service Discovery)
- Apache Kafka (Message Broker)

## Prerequisites

Before running this application, ensure you have the following installed:

- Java Development Kit (JDK) 17 or higher
- Maven 3.6+
- Docker & Docker Compose
- MySQL 8.0+
- IDE (IntelliJ IDEA, Eclipse, or VS Code recommended)

## Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/cneluhena/Patient-Management-System.git
cd Patient-Management-System
```

### 2. Setup Environment Variables

Create a `.env` file in the root directory with the following variables:

```env
# Database Configuration
MYSQL_ROOT_PASSWORD=rootpassword
MYSQL_DATABASE=patient_management_db
MYSQL_USER=pmsuser
MYSQL_PASSWORD=pmspassword

# Keycloak Configuration
KEYCLOAK_ADMIN=admin
KEYCLOAK_ADMIN_PASSWORD=admin123
KEYCLOAK_DB_USER=keycloak
KEYCLOAK_DB_PASSWORD=keycloakpassword

# Kafka Configuration
KAFKA_BROKER_ID=1
KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181

# Application Configuration
EUREKA_SERVER_URL=http://localhost:8761/eureka
```

**Important**: Make sure to add these variables as environment variables or use the `.env` file with Docker Compose.

### 3. Start Keycloak Server

Navigate to the `auth-service` directory and start Keycloak using Docker Compose:

```bash
cd auth-service
docker-compose up -d
```

Keycloak will be available at `http://localhost:8080`

- **Admin Console**: `http://localhost:8080/admin`
- **Username**: admin (or value from `KEYCLOAK_ADMIN`)
- **Password**: admin123 (or value from `KEYCLOAK_ADMIN_PASSWORD`)

### 4. Start Kafka Server

Return to the root directory and start Kafka using Docker Compose:

```bash
cd ..
docker-compose up -d
```

This will start both Kafka and Zookeeper services.

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

## Service Ports

| Service | Port |
|---------|------|
| Eureka Server | 8761 |
| Auth Service | 8080 |
| Doctor Service | 8081 |
| Patient Service | 8082 |
| Appointment Service | 8083 |
| Keycloak | 8080 |
| Kafka | 9092 |

## Project Structure

```
Patient-Management-System/
├── auth-service/              # Authentication & Authorization
│   ├── docker-compose.yml     # Keycloak setup
│   └── src/
├── doctor-service/            # Doctor management
│   └── src/
├── patient-service/           # Patient management
│   └── src/
├── appointment-service/       # Appointment scheduling
│   └── src/
├── service-discovery/         # Eureka Server
│   └── src/
├── docker-compose.yml         # Kafka & Zookeeper setup
├── .env                       # Environment variables
└── README.md
```

## API Gateway

All microservices are accessed through the API Gateway (if configured). Direct service access:

- **Doctor Service**: `http://localhost:8081/api/doctors`
- **Patient Service**: `http://localhost:8082/api/patients`
- **Appointment Service**: `http://localhost:8083/api/appointments`

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

### Kafka connectivity problems
- Ensure Kafka and Zookeeper containers are running
- Verify Kafka broker is accessible on port 9092

## Stopping Services

To stop all Docker containers:

```bash
docker-compose down
```

To stop individual microservices, use `Ctrl+C` in their respective terminal windows.

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contact

**Developer**: cneluhena

**GitHub**: [https://github.com/cneluhena](https://github.com/cneluhena)

**Project Link**: [https://github.com/cneluhena/Patient-Management-System](https://github.com/cneluhena/Patient-Management-System)

## Acknowledgments

- Spring Boot & Spring Cloud Documentation
- Keycloak Community
- Apache Kafka Project
- Netflix Eureka
- All contributors who have helped with this project

---

**Note**: This is an educational/demonstration project. For production use in healthcare settings, ensure compliance with relevant regulations (HIPAA, GDPR, etc.) and implement appropriate security measures.
