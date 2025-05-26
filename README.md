# 🧾 Invoice System – Angular & Spring Boot

Invoice system built with Angular 19 and Spring Boot. The system allows invoice generation, QR code scanning for automatic data input, and supports downloading invoices in XML format. It includes robust validations and provides a user-friendly experience for managing multiple tickets.

## Tech Stack

![Angular](https://img.shields.io/badge/angular-%23DD0031.svg?style=for-the-badge&logo=angular&logoColor=white) ![Spring Boot](https://img.shields.io/badge/springboot-%236DB33F.svg?style=for-the-badge&logo=springboot&logoColor=white) ![PostgreSQL](https://img.shields.io/badge/postgresql-%23336791.svg?style=for-the-badge&logo=postgresql&logoColor=white) ![TypeScript](https://img.shields.io/badge/typescript-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white)   ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white) ![Docker](https://img.shields.io/badge/docker-%23007ACC.svg?style=for-the-badge&logo=docker&logoColor=white)

## DEMO


## ⚙️ Setup Instructions
### 1. PostgreSQL Database
- Create a database named: `ticket-db`

### 2. Backend Configuration

- Configure your `application.properties` file with your database credentials:
  
  ```properties
  spring.datasource.url=jdbc:postgresql://localhost:5432/ticket-db
  spring.datasource.username=your_username
  spring.datasource.password=your_password
  ```

### 3. Insert initial Data
Execute the SQL scripts:
- INSERT_EstadosMexico.sql
- INSERT_RegimenFiscal.sql
- INSERT_UsoCFDI.sql
- Use the endpoint from the Back end API to insert branches (sucursales)
  
  ```
  POST/dataseed
  ```
### 4. Start the Backend
- Run the Spring Boot application.

### 5. Frontend Setup
- Install dependencies:
 ```
npm install
```
Check dockerConfig branch to see Docker configuration
