# Portfolio-App

## Project Overview
The **Portfolio-App** is a personal portfolio management system that allows users to register, log in, and manage their portfolios. Each user can create and maintain a single portfolio. Upon successful authentication via **JWT** (JSON Web Token), users are authorized to perform CRUD (Create, Read, Update, Delete) operations on their portfolio.

Built with **Spring Boot**, this app integrates powerful libraries for secure authentication, API documentation, and efficient data handling.

## Key Features
- **User Authentication**: Register and log in to the application.
- **JWT Token Authentication**: Secure, stateless communication with JWT.
- **Portfolio Management**: CRUD operations for user portfolios.
- **Single Portfolio per User**: Ensures each user can only have one portfolio.
- **API Documentation**: Swagger UI for easy API exploration and testing.

## Technology Stack
- **Backend Framework**: Spring Boot (3.4.1, Java 17)
- **Database**: PostgreSQL
- **Authentication**: JWT (JSON Web Token)
- **Validation**: Spring Boot Validation
- **API Documentation**: Swagger/OpenAPI
- **Data Mapping**: MapStruct
- **Development Tools**: Lombok, Spring Security, Spring Data JPA

## Installation Guide

### Prerequisites
- **Java 17** or above
- **Maven** for dependency management and building the project
- **PostgreSQL** (Make sure you have a PostgreSQL instance set up)

### Steps to Setup the Project Locally

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/sehrivaliyeva/Portfolio-App.git
    ```

2. **Navigate to the Project Directory**:
    ```bash
    cd Portfolio-App
    ```

3. **Configure Database Connection**:
    Update your `application.properties` or `application.yml` with the following database credentials:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/portfolio_db
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```

4. **Run the Application**:
    Start the application with:
    ```bash
    mvn spring-boot:run
    ```

    The app will be available at `http://localhost:8080`.

### Swagger UI for API Documentation
Once the application is running, you can access the Swagger UI for API documentation and testing:
