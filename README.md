
# Spring Boot CRUD Application with H2 Database

This is a simple Spring Boot application that demonstrates basic CRUD (Create, Read, Update, Delete) operations using a REST API with an H2 in-memory database. The application utilizes **Spring Data JPA**, **Lombok**, and **Spring Web** to build a backend for managing employee records.

---

## Features

- **CRUD operations** on Employee records:
  - Create a new employee
  - Retrieve all employees
  - Retrieve a single employee by ID
  - Update an employee
  - Delete an employee
- Uses **H2 Database** for in-memory persistence.
- **Spring Data JPA** for database operations.
- **Spring Boot** for rapid application development.
- **Lombok** for reducing boilerplate code.
- **Swagger UI** for interactive API documentation.

---

## Technologies Used

- **Java 21**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **Spring Web**
- **H2 Database**
- **Lombok**
- **Gradle**
- **Swagger (Springdoc)**

---

## Prerequisites

1. **Java 21 or higher** installed.
2. **Gradle** installed.
3. An IDE like **IntelliJ IDEA** or **Eclipse** (optional).

---

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo-name.git
   cd your-repo-name
   ```

2. Build and run the application:
   ```bash
   ./gradlew bootRun
   ```

3. Access the application in your browser or API testing tool (e.g., Postman) at:
   ```
   http://localhost:8080
   ```

4. Swagger UI (API Documentation) is available at:
   ```
   http://localhost:8080/swagger-ui.html
   ```

---

## Endpoints

### **Base URL:** `http://localhost:8080/api/employees`

| HTTP Method | Endpoint         | Description                          |
|-------------|------------------|--------------------------------------|
| GET         | `/`              | Retrieve all employees.              |
| GET         | `/{id}`          | Retrieve a single employee by ID.    |
| POST        | `/`              | Create a new employee.               |
| PUT         | `/{id}`          | Update an existing employee.         |
| DELETE      | `/{id}`          | Delete an employee by ID.            |

---

### Request and Response Format

#### **EmployeeDTO**
```json
{
  "id": 1,
  "name": "John Doe",
  "department": "Engineering",
  "salary": 75000.0
}
```

#### **Sample Requests**

1. **Create Employee (POST /api/employees)**
   Request Body:
   ```json
   {
       "name": "Jane Doe",
       "department": "HR",
       "salary": 60000
   }
   ```

   Response:
   ```json
   {
       "id": 1,
       "name": "Jane Doe",
       "department": "HR",
       "salary": 60000
   }
   ```

2. **Retrieve All Employees (GET /api/employees)**
   Response:
   ```json
   [
       {
           "id": 1,
           "name": "Jane Doe",
           "department": "HR",
           "salary": 60000
       }
   ]
   ```

3. **Update Employee (PUT /api/employees/1)**
   Request Body:
   ```json
   {
       "name": "Jane Smith",
       "department": "Finance",
       "salary": 70000
   }
   ```

   Response:
   ```json
   {
       "id": 1,
       "name": "Jane Smith",
       "department": "Finance",
       "salary": 70000
   }
   ```

4. **Delete Employee (DELETE /api/employees/1)**
   Response: `204 No Content`

---

## Project Structure

```
src/
├── main/
│   ├── java/com/example/demo/
│   │   ├── controller/         # REST controllers
│   │   ├── dto/                # Data Transfer Objects
│   │   ├── model/              # Entity classes
│   │   ├── repository/         # JPA repositories
│   │   ├── service/            # Service layer
│   │   └── DemoApplication.java # Main Spring Boot application
│   └── resources/
│       ├── application.properties # Application configuration
│       └── data.sql              # Sample data for testing
└── test/
    ├── java/com/example/demo/
    │   ├── controller/         # Controller tests
    │   ├── service/            # Service layer tests
    │   └── integration/        # Integration tests
```

---

## H2 Database Console

You can access the H2 Database console to view or query your data.

- **URL:** `http://localhost:8080/h2-console`
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Username:** `sa`
- **Password:** *(leave empty)*

---

## Testing

### Running Tests
To run the tests, execute:
```bash
./gradlew test
```

### Code Coverage
Add the JaCoCo plugin for code coverage:
```gradle
plugins {
    id 'jacoco'
}

jacocoTestReport {
    dependsOn test
    reports {
        html.required = true
        xml.required = true
    }
}
```

Generate the coverage report:
```bash
./gradlew jacocoTestReport
```

Open the report:
```
build/reports/jacoco/test/html/index.html
```

---

## Contributing

1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Commit your changes and push to your fork.
4. Open a pull request.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Author

[Ramu Donikana](https://github.com/ramu-donikana)

---

