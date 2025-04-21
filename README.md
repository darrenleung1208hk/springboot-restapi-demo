# Spring Boot REST API Demo

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
    "errors": [
        "Title is required",
        "Author must contain only alphanumeric characters and spaces"
    ]
}
```

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