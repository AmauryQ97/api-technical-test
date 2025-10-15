#!/bin/bash

# Database configuration
DB_NAME="technical_test_db"
DB_USER="api_user"
DB_PASSWORD="api_password"
DB_HOST="localhost"
DB_PORT="5432"

# Check if PostgreSQL is running
if ! pg_isready -h $DB_HOST -p $DB_PORT &>/dev/null; then
    echo "PostgreSQL is not running. Please start PostgreSQL and try again."
    exit 1
fi

# Create database and user
echo "Setting up database..."
sudo -u postgres psql -c "CREATE DATABASE $DB_NAME;" 2>/dev/null || echo "Database already exists or creation failed"
sudo -u postgres psql -c "CREATE USER $DB_USER WITH ENCRYPTED PASSWORD '$DB_PASSWORD';" 2>/dev/null || echo "User already exists or creation failed"
sudo -u postgres psql -c "GRANT ALL PRIVILEGES ON DATABASE $DB_NAME TO $DB_USER;"

echo "\nYou can now start the application with: mvn spring-boot:run"
