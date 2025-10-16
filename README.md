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
   - API Documentation: http://localhost:8080/api/swagger-ui.html
   - Health Check: http://localhost:8080/api/health

## API Documentation

API documentation is available at `/swagger-ui.html` when the application is running.

## Architecture

I implemented this simple API using the MVC architecture.  
I tried to keep the code as simple and clear as possible by using decorators to reduce boilerplate, implement properties validation and handle errors.
About active contracts, I decided to include the one with null endDate as I think it is a valid case.

## Proof it is working

I have been able to create/update/delete/get clients then associate contracts. I am also able to create contracts without clients or endDate.
When deleting the clients, all contract related get the current date as endDate and client is set to null.
I can also get the sum cost of all active contracts for a client.
You can find multiple screenshots showing the API in action.
