# Journal Application 🚀

A secure REST API for personal journal management built with Spring Boot. The application enables users to create and manage journal entries securely using JWT-based authentication and role-based authorization. It also integrates MongoDB, Redis caching, email scheduling, weather information, sentiment analysis, and interactive API documentation using Swagger OpenAPI.

---

## 🚀 Features

- User Registration & Login
- JWT-based Authentication & Authorization
- Role-Based Access Control (User/Admin)
- Secure Password Encryption using BCrypt
- CRUD Operations for Journal Entries
- MongoDB Atlas Integration
- Redis Caching
- Weather API Integration
- Sentiment Analysis for Journal Entries
- Automated Email Notifications
- Background Task Scheduling
- Global Exception Handling
- Request Validation
- Interactive API Documentation using Swagger OpenAPI
- Layered Architecture (Controller → Service → Repository)

---

## 🛠️ Tech Stack

- Java 21
- Spring Boot 3
- Spring Security
- JWT (JSON Web Token)
- Spring Data MongoDB
- MongoDB Atlas
- Redis
- Swagger OpenAPI
- Maven
- Lombok

---

## 🔐 Security

- JWT Authentication & Authorization
- Role-Based Access Control (User/Admin)
- Stateless Session Management
- BCrypt Password Encryption
- Protected REST APIs using Spring Security

---

## 📂 Project Structure

```text
src
├── controller
├── service
├── repository
├── entity
├── dto
├── config
├── scheduler
├── cache
├── exception
├── enums
├── constraints
└── utilis
```

## ⚙️ Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/Arushi-ydv/journalApp.git
```

### 2. Configure the Application

Update the required configuration values in your `application.yml` or `application.properties` file:

- MongoDB Atlas URI
- Redis Configuration
- JWT Secret Key
- Email Credentials
- Weather API Key

### 3. Build and Run

Using Maven:

```bash
mvn spring-boot:run
```

Or run the `JournalApplication.java` file directly from your IDE.

### 4. Access Swagger UI

```text
http://localhost:8080/journal/swagger-ui/index.html
```

## 👩‍💻 Author

**Arushi Yadav**

GitHub: https://github.com/Arushi-ydv
