# JournalApp

A secure RESTful backend application for managing personal journal entries, built with Java and Spring Boot. The application provides JWT-based authentication, role-based authorization, journal entry management, request validation, global exception handling, and interactive API documentation.

## Features

- User Registration and Login
- JWT-based Authentication and Authorization
- Role-Based Access Control (User/Admin)
- Secure Password Encryption using BCrypt
- Create, Read, Update, and Delete Journal Entries
- MySQL Database Integration using Spring Data JPA and Hibernate
- Request Validation
- Global Exception Handling
- Protected REST APIs
- Interactive API Documentation using Swagger/OpenAPI
- API Testing using Postman
- Layered Backend Architecture

## Technologies Used

### Backend

- Java 21
- Spring Boot 3
- Spring Security
- Spring Data JPA
- Hibernate
- JWT (JSON Web Token)

### Database

- MySQL

### API Documentation & Testing

- Swagger/OpenAPI
- Postman

### Build & Development Tools

- Maven
- Lombok

## Architecture

JournalApp follows a layered backend architecture that separates request handling, business logic, and data access.

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
MySQL Database
```

### Main Components

- **Controllers** – Handle REST API requests and responses.
- **Services** – Implement business logic and application operations.
- **Repositories** – Handle database access using Spring Data JPA.
- **DTOs** – Transfer data between API layers.
- **Entities** – Represent application data and database records.
- **Configuration** – Contains application and security configuration.
- **Exceptions** – Handles application-specific and global exceptions.
- **Utils** – Contains supporting utility components.

## Security

Spring Security is used to secure the application's REST APIs.

- JWT-based authentication
- Role-based authorization for User and Admin roles
- Stateless session management
- BCrypt password encryption
- Protected API endpoints

## API Documentation

The REST APIs are documented using Swagger/OpenAPI.

After starting the application, Swagger UI can be accessed locally at:

```text
/journal/swagger-ui/index.html
```

Swagger provides an interactive interface for viewing and testing the available API endpoints.

## Database

JournalApp uses MySQL for persistent data storage.

Spring Data JPA and Hibernate are used for object-relational mapping and database operations.

### Main Tables

- `users`
- `user_roles`
- `journal_entries`

## Project Structure

```text
src/main/java
│
├── config
├── controller
├── dto
├── entity
├── enums
├── exception
├── repository
├── service
└── utils
```

## Getting Started

### Prerequisites

- Java 21
- Maven
- MySQL
- Git

### Setup

1. Clone the repository.
2. Create a MySQL database for the application.
3. Configure the required database and application properties.
4. Build the project using Maven.
5. Run the Spring Boot application.
6. Access the REST APIs through Swagger UI or test them using Postman.

## Configuration

Configure the required database and security properties in the application's configuration file.

Do not commit passwords, database credentials, JWT secrets, or other sensitive information to the repository.

## API Testing

The REST APIs can be tested using:

- Swagger/OpenAPI
- Postman

## Author

**Arushi Yadav**
