# Vendors API
This repository contains a RESTful API for managing sellers, offering full CRUD (Create, Read, Update, Delete) operations. The API is designed to be easy to integrate with, providing endpoints to handle seller data efficiently.

Features:

Create: Add new vendors with comprehensive data fields.

Read: Retrieve detailed information about individual or multiple vendors.

Update: Modify existing vendors information.

Delete: Remove vendors from the database.

## Technologies Used:
- **Java 21**
- **Spring Boot 3**
- **Maven**
- **Docker** & **Docker Compose**
- **PostgresSQL**
- **OpenAPI** (contract in `src/main/resources/vendors.yml`)
- **Prometheus** & **Grafana**
- **Junit 5**

## Step-by-step start guide
### 1. Clone the repository
Git clone the repository, use clean and install to verify that maven downloaded the required dependencies
### 2. Start docker
Using `docker-compose up -d`, to initialize containers with database, external API, Grafana and Prometheus
### 3. Run the application
Run the application in debug mode if necessary.

## API Endpoints:
All API endpoints can be found in `src/main/resources/vendors.yml`
- **POST example**
utilizing the following payload and URI to create a new vendor: 
`localhost:8080/v1/vendor`
```
{
  "name": "John",
  "birthday": "01-01-2017",
  "document": "CPF ou CNPJ",
  "email": "rese@email.com",
  "contractType": "outsourcing",
  "branch": "branch name"
}
```

