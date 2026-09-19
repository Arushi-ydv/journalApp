# Journal Application 🚀

A secure RESTful backend application for managing personal journal entries built with Spring Boot. The application provides JWT-based authentication, role-based authorization, journal management APIs, request validation, global exception handling, and interactive API documentation using Swagger OpenAPI.

---

## 🚀 Features

- User Registration & Login
- JWT-based Authentication & Authorization
- Role-Based Access Control (User/Admin)
- Secure Password Encryption using BCrypt
- CRUD Operations for Journal Entries
- MySQL Database Integration using Spring Data JPA
- Request Validation
- Global Exception Handling
- Interactive API Documentation using Swagger OpenAPI
- Layered Architecture (Controller → Service → Repository)

---

## 🛠️ Tech Stack

- Java 21
- Spring Boot 3
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL
- JWT (JSON Web Token)
- Swagger OpenAPI
- Maven
- Lombok

---

## 🔐 Security

- JWT-based Authentication
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
├── service
└── utils
```

---

## 📌 API Documentation

Swagger UI:

[http://localhost:8080/journal/swagger-ui/index.html](http://localhost:8080/journal/swagger-ui/index.html)

---

## 🗄️ Database

MySQL is used as the database with Spring Data JPA and Hibernate.

Main tables:

- `users`
- `user_roles`
- `journal_entries`

---

## 👤 Author

**Arushi Yadav**