# ClipronTodoApp

A modern, secure Todo application built with Spring Boot that helps you organize tasks efficiently. This application provides user authentication, todo list management, and notification capabilities through a RESTful API.

## Features

- **User Authentication**: Secure JWT-based authentication system
- **Todo Management**: Create, read, update, and delete todo items
- **Todo Lists**: Organize todos into different lists
- **Priority Levels**: Assign different priority levels to todos
- **Notifications**: Receive notifications for important todos and updates
- **RESTful API**: Well-structured API endpoints for all functionality
- **OpenAPI Documentation**: Comprehensive API documentation using Swagger/OpenAPI
- **Secure by Design**: Implements Spring Security best practices

## Technologies Used

- **Java 17**
- **Spring Boot 3.4.5**
- **Spring Security** with JWT Authentication
- **Spring Data JPA** for data persistence
- **PostgreSQL** database for data storage
- **Lombok** for reducing boilerplate code
- **Swagger/OpenAPI** for API documentation
- **Maven** for dependency management and building
- **Docker** for containerization

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- PostgreSQL database (or Docker for containerized deployment)
- Git

## Environment Variables

The application requires the following environment variables to be set:

- `DATASOURCE_URL`: PostgreSQL database URL (e.g., jdbc:postgresql://localhost:5432/todoapp)
- `DB_USERNAME`: Database username
- `DB_PASSWORD`: Database password
- `SECRET_KEY`: Secret key for JWT token generation and validation
- `SERVER_PORT`: Port on which the application will run (default: 8080)

You can set these environment variables in various ways:
- In your development environment
- In your deployment environment configuration
- In a `.env` file (make sure to add this to `.gitignore`)
- As command-line arguments when running the application

## Installation & Setup

### 1. Clone the repository

```bash
git clone https://github.com/Waffiyyi/clipron_todo-app_backend
```

### 2. Configure the database

Create a PostgreSQL database and set up the required environment variables as specified in the [Environment Variables](#environment-variables) section.

You can create a `.env` file in the project root with the following content:

```properties
DATASOURCE_URL=jdbc:postgresql://localhost:5432/your_db_name
DB_USERNAME=your_username
DB_PASSWORD=your_password
SECRET_KEY=your-secret-key
SERVER_PORT=8080
```

### 3. Build the application

```bash
mvn clean package
```

### 4. Run the application

```bash
java -jar target/clipronTodoApp.jar
```

Alternatively, you can run it directly using Maven:

```bash
mvn spring-boot:run
```

The application will be available at http://localhost:8080

## API Documentation

The API documentation is available via Swagger UI at:

```
http://localhost:8080/swagger-ui.html
```

You can check out the live version deployed on Koyeb at: 

```
https://coastal-turkey-donezo-todo-app-69b1821d.koyeb.app/swagger-ui/index.html
```
This documentation provides a detailed overview of all available endpoints, request/response formats, and allows you to test the API directly from the browser.

## Docker Setup

### Build the Docker image

```bash
docker build -t clipron-todoapp:latest .
```

### Run the application using Docker

```bash
docker run -p 8080:8080 \
  -e DATASOURCE_URL=jdbc:postgresql://your-db-host:5432/your-db-name \
  -e DB_USERNAME=your-username \
  -e DB_PASSWORD=your-password \
  -e SECRET_KEY=your-secret-key \
  -e SERVER_PORT=8080 \
  clipron-todoapp:latest
```

### Using Docker Compose

Create a `docker-compose.yml` file:

```yaml
version: '3'
services:
  app:
    image: clipron-todoapp:latest
    ports:
      - "8080:8080"
    environment:
      - DATASOURCE_URL=jdbc:postgresql://db:5432/todoapp
      - DB_USERNAME=postgres
      - DB_PASSWORD=postgres
      - SECRET_KEY=your-secret-key
      - SERVER_PORT=8080
    depends_on:
      - db
  db:
    image: postgres:14
    ports:
      - "5432:5432"
    environment:
      - POSTGRES_DB=todoapp
      - POSTGRES_USER=postgres
      - POSTGRES_PASSWORD=postgres
    volumes:
      - postgres-data:/var/lib/postgresql/data

volumes:
  postgres-data:
```

Run with:

```bash
docker-compose up
```

## Contributing

Contributions to improve the application are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License.

