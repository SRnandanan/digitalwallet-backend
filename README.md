# 🚀 Digital Wallet Backend

## 🏆 Overview
A **secure and scalable** digital wallet backend built using **Spring Boot**, featuring **robust authentication** and **encryption mechanisms**. Users can register and transfer money to each other seamlessly.

## 🔥 Features
✔️ **JWT Authentication** – Secure API access with token-based authentication.  
✔️ **SHA-256 Password Encryption** – Ensures secure credential storage.  
✔️ **RESTful APIs** – Manage users, accounts, and peer-to-peer transfers seamlessly.

## 📌 Key API Endpoints

| **Endpoint**          | **Method** | **Description**                  |
|----------------------|-----------|----------------------------------|
| `/api/user/sign-up` | **POST**   | Register a new user             |
| `/api/user/login`    | **POST**   | Logs in user and receives JWT token for authentication    |
| `/api/transaction/create` | **POST** | Transfer money to another user  |

## ⚙️ Installation
```bash
git clone <repository-url>
cd digital-wallet-backend
mvn install
mvn spring-boot:run
```

## 🛠️ Technologies Used


- 🏗️ **Spring Boot**
- 🔐 **JWT Authentication**
- 🔑 **SHA-256 Encryption**
- 📦 **Maven**

