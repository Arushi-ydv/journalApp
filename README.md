# Journal Application 🚀

A Spring Boot backend application for managing personal journal entries with JWT-based authentication, MongoDB integration, Redis caching, automated email notifications, background scheduling, and interactive API documentation using Swagger OpenAPI.

---

## 🚀 Features

- User Registration & Login
- JWT-based Authentication & Authorization
- Secure Password Encryption using BCrypt
- CRUD Operations for Journal Entries
- MongoDB Integration
- Redis Caching
- Automated Email Notifications
- Background Task Scheduling
- RESTful API Design
- Interactive API Documentation using Swagger OpenAPI
- Layered Architecture (Controller → Service → Repository)

---

## 🛠️ Tech Stack

- Java 21
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- MongoDB
- Redis
- Swagger OpenAPI
- Maven
- JUnit

---

## 🔐 Security

- JWT Authentication
- Stateless Session Management
- Spring Security
- BCrypt Password Encoding
- Protected REST APIs

---

## 📖 API Documentation

After running the application, access the API documentation at:

### Swagger UI

```text
http://localhost:8080/journal/swagger-ui/index.html
```

### OpenAPI JSON

```text
http://localhost:8080/journal/v3/api-docs
```

---

## 📂 Project Structure

- Controller Layer
- Service Layer
- Repository Layer
- Security (JWT & Spring Security)
- Configuration
- Scheduler
- Redis Cache
- Email Service
- MongoDB Database

---

## ⚙️ How to Run

### 1. Clone the repository

```bash
git clone https://github.com/Arushi-ydv/journalApp.git
```

### 2. Configure the following environment variables

```text
MONGO_URI
JWT_SECRET
EMAIL_USER
EMAIL_PASS
```

### 3. Run the application

Run the `JournalApplication.java` file or use Maven:

```bash
mvn spring-boot:run
```

### 4. Open Swagger UI

```text
http://localhost:8080/journal/swagger-ui/index.html
```

---

## 👩‍💻 Author

**Aarushi Yadav**

Aspiring Java Backend Developer

GitHub: https://github.com/Arushi-ydv
