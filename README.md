# Technical Test API

A Spring Boot 3.2.0 application with Java 21, PostgreSQL, and Swagger UI.

## Prerequisites

- Java 21 or later
- Maven 3.6.3 or later
- PostgreSQL 15 or later
- Bash (for setup script)

## Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd api-technical-test
   ```

2. **Set up the database**
   - Run the setup script (requires sudo privileges for PostgreSQL):
     ```bash
     chmod +x setup-db.sh
     ./setup-db.sh
     ```

## Running the Application

1. **Build the application**
   ```bash
   mvn clean install
   ```

2. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

3. **Access the application**
   - API Documentation: http://localhost:8080/swagger-ui.html
   - Health Check: http://localhost:8080/api/health

## API Documentation

API documentation is available at `/swagger-ui.html` when the application is running.
