# 🧦 Chaussettes Sales API

A Spring Boot REST API that implements a simple algorithm.

## 📋 Table of Contents

- [API Documentation](#api-documentation)
- [Project Structure](#project-structure)
- [Running Tests](#running-tests)
- [Error Handling](#error-handling)


## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+

### Installation

1. **Clone the repository**
```bash
   git clone https://github.com/yourusername/chaussettes-sales-api.git
   cd chaussettes-sales-api
```

2. **Build the project**
```bash
   mvn clean install
```

3. **Run the application**
```bash
   mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 📖 API Documentation

### Get Chaussettes

Generates a list of strings based on the algorithm rules.

**Endpoint:** `GET /api/exercices/chaussettes`

**Parameters:**
| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `start` | int | 0 | Starting index of the sequence |
| `end` | int | 100 | Ending index of the sequence |

**Rules:**
- If number is divisible by 3 → returns "Chaussettes"
- If number is divisible by 5 → returns "Sales"
- If number is divisible by both 3 and 5 → returns "ChaussettesSales"
- Otherwise → returns the number as a string

**Example Request:**
```bash
curl http://localhost:8080/api/exercices/chaussettes?start=1&end=15
```

**Example Response:**
```json
[
  "1",
  "2",
  "Chaussettes",
  "4",
  "Sales",
  "Chaussettes",
  "7",
  "8",
  "Chaussettes",
  "Sales",
  "11",
  "Chaussettes",
  "13",
  "14",
  "ChaussettesSales"
]
```

**Error Response (400 Bad Request):**
```json
{
  "timestamp": "2026-01-06T14:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Start value (10) must be less than or equal to end value (5)",
  "path": "/api/exercices/chaussettes"
}
```

## 📁 Project Structure
```
src/
├── main/
│   └── java/
│       └── com.spagnou.itsf.api/
│           ├── controllers/
│           │   └── ChaussetteController.java
│           ├── services/
│           │   └── ChaussetteService.java
│           ├── exceptions/
│           │   ├── GlobalExceptionHandler.java
│           │   ├── ErrorResponse.java
│           │   └── InvalidRangeException.java
│           └── ItsfApplication.java
└── test/
    └── java/
        └── com.spagnou.itsf.api/
            ├── controllers/
            │   └── ChaussetteControllerTest.java
            ├── services/
            │   └── ChaussetteServiceTest.java
            └── exceptions/
                └── GlobalExceptionHandlerTest.java
```

## 🧪 Running Tests

Run all tests:
```bash
mvn test
```


## 🛡️ Error Handling

The API includes comprehensive error handling:

### Validation Errors (400 Bad Request)
- Start value greater than end value
- Range exceeds maximum allowed (10,000 elements)

### Server Errors (500 Internal Server Error)
- Unexpected exceptions with standardized error response

All errors return a consistent JSON structure:
```json
{
  "timestamp": "ISO-8601 datetime",
  "status": "HTTP status code",
  "error": "Error type",
  "message": "Detailed error message",
  "path": "Request path"
}
```
