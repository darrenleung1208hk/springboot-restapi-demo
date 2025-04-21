# Spring Boot REST API Demo
[![CI Pipeline](https://github.com/darrenleung1208hk/springboot-restapi-demo/actions/workflows/ci.yml/badge.svg)](https://github.com/darrenleung1208hk/springboot-restapi-demo/actions/workflows/ci.yml)

A RESTful API demo built with Spring Boot for managing books. This application demonstrates best practices in building REST APIs with validation, error handling, and proper response formatting.

## Prerequisites

- Java 17 or higher
- Maven 3.6+ (or use the included Maven wrapper)

## Building the Application

You can build the application using either Maven or the Maven wrapper:

Using Maven:
```bash
mvn clean install
```

Using Maven wrapper:
```bash
./mvnw clean install  # For Unix-based systems
.\mvnw clean install  # For Windows
```

## Running the Application

After building, you can run the application using:

```bash
./mvnw spring-boot:run  # For Unix-based systems
.\mvnw spring-boot:run  # For Windows
```

The application will start on `http://localhost:8080`

## Database

The application uses H2 in-memory database. You can access the H2 console at:
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:booksdb`
- Username: `sa`
- Password: (leave empty)

## API Endpoints

### Books API

#### Create a Book
- **POST** `/api/books`
- Request body:
```json
{
    "title": "Sample Book",
    "author": "John Doe",
    "published": true
}
```

#### Get All Books
- **GET** `/api/books`
- Supports filtering through query parameters:
  - `author`: Filter books by author name
  - `published`: Filter books by publication status (true/false)

Example requests:
```bash
# Get all books
GET /api/books

# Filter by author
GET /api/books?author=John%20Doe

# Filter by published status
GET /api/books?published=true

# Filter by both author and published status
GET /api/books?author=John%20Doe&published=true
```

Response format:
```json
[
    {
        "id": 1,
        "title": "Sample Book",
        "author": "John Doe",
        "published": true
    }
]
```

#### Delete a Book
- **DELETE** `/api/books/{id}`

### Validation

The API includes validation for:
- Title (required, alphanumeric with spaces)
- Author (required, alphanumeric with spaces)
- Published (boolean value)

### Error Responses

The API returns appropriate HTTP status codes and error messages:
- 400 Bad Request: For validation errors
- 404 Not Found: When a requested resource doesn't exist
- 500 Internal Server Error: For server-side errors

Error response format:
```json
{
    "timestamp": "2024-04-21T14:30:00.000+00:00",
    "status": 400,
    "error": "Validation Error",
    "message": "title: Title is required; author: Author must contain only alphanumeric characters and spaces",
    "validationErrors": [
        {
            "field": "title",
            "message": "Title is required"
        },
        {
            "field": "author",
            "message": "Author must contain only alphanumeric characters and spaces"
        }
    ],
    "path": "/api/books"
}
```

### Screenshots for the API Calls
![Screenshot 2025-04-22 031229](https://github.com/user-attachments/assets/cef607ca-1b26-4fb3-bade-f989682b089e)
![Screenshot 2025-04-22 031300](https://github.com/user-attachments/assets/4452538f-b8d7-412c-8cce-d8d04bbd5d11)
![Screenshot 2025-04-22 031310](https://github.com/user-attachments/assets/67e6364d-1630-45fb-99fd-342d580ff647)
![Screenshot 2025-04-22 031318](https://github.com/user-attachments/assets/7e10bb17-557b-4117-a361-9fc01777e30b)
![Screenshot 2025-04-22 031327](https://github.com/user-attachments/assets/1530606c-3d2f-464b-b2f9-949101de19d2)
![Screenshot 2025-04-22 031201](https://github.com/user-attachments/assets/786af9c9-20b2-4dbb-ad16-72917f79eff7)


## Running Tests

To run the tests:

```bash
./mvnw test  # For Unix-based systems
.\mvnw test  # For Windows
```

## Project Structure

```
src/
├── main/
│   └── java/
│       └── io/
│           └── darrenleung/
│               └── springboot_restapi_demo/
│                   ├── controllers/    # REST controllers
│                   ├── services/       # Business logic
│                   ├── repositories/   # Data access
│                   ├── models/         # Domain models
│                   ├── dtos/          # Data Transfer Objects
│                   ├── exception/     # Exception handling
│                   └── serialization/ # Custom serializers
└── test/
    └── java/      # Test classes
``` 