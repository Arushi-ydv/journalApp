# Journal Application 🚀

A secure RESTful backend application for managing personal journal entries built with Spring Boot. The application provides JWT-based authentication, role-based authorization, journal management APIs, request validation, global exception handling, email notification support, and interactive API documentation using Swagger OpenAPI.

---

## 🚀 Features

- User Registration & Login
- JWT-based Authentication & Authorization
- Role-Based Access Control (User/Admin)
- Secure Password Encryption using BCrypt
- CRUD Operations for Journal Entries
- MongoDB Atlas Integration
- Email Notification Support
- Request Validation
- Global Exception Handling
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
- Swagger OpenAPI
- Maven
- Lombok

---

## 🔐 Security

- JWT Authentication
- Role-Based Authorization
- Stateless Session Management
- BCrypt Password Encryption
- Protected REST APIs

---

## 📂 Project Structure

```text
src
├── config
├── controller
├── dto
├── entity
├── enums
├── exception
├── repository
├── scheduler
├── service
└── utilis
```

---

## ⚙️ Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/Arushi-ydv/journalApp.git
```

### 2. Configure Environment Variables

Configure the following environment variables:

- `MONGO_URI`
- `JWT_SECRET`
- `JWT_EXPIRATION`
- `EMAIL_USER`
- `EMAIL_PASS`

### 3. Build and Run

Using Maven:

```bash
mvn spring-boot:run
```

Or run the `JournalApplication.java` file directly from your IDE.

---

## 📖 API Documentation

After starting the application, access Swagger UI:

```text
http://localhost:8080/journal/swagger-ui/index.html
```

OpenAPI Specification:

```text
http://localhost:8080/journal/v3/api-docs
```

---

## 📌 Future Improvements

- DTO Refactoring for all APIs
- Pagination & Sorting
- Search APIs
- Docker Support
- Cloud Deployment

---

## 👩‍💻 Author

**Arushi Yadav**

GitHub: https://github.com/Arushi-ydv