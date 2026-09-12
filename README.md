# 🔗 URL Shortener

A backend URL Shortener built using **Java, Spring Boot, Spring Data JPA, Hibernate, and MySQL**.

The application converts long URLs into short, shareable URLs and redirects users to the original URL when the short URL is accessed.

---

## 🚀 Features

- Create short URLs from long URLs
- Redirect short URLs to the original URLs
- URL validation
- Global exception handling
- Custom URL-not-found exception
- Unique short-code generation
- Short-code collision protection
- URL expiration support
- Click-count tracking
- Persistent storage using MySQL
- RESTful API design

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 | Programming Language |
| Spring Boot 4.1.1 | Backend Framework |
| Spring MVC | REST API development |
| Spring Data JPA | Database interaction |
| Hibernate | ORM |
| MySQL | Relational Database |
| Maven | Build & Dependency Management |
| Postman | API Testing |
| Git & GitHub | Version Control |

---

## 🏗️ Project Architecture

```text
Client
  │
  ▼
Controller
  │
  ▼
Service
  │
  ▼
Repository
  │
  ▼
Hibernate / JPA
  │
  ▼
MySQL
```

The application follows a layered architecture:

```text
controller
    ↓
service
    ↓
repository
    ↓
entity
    ↓
database
```

---

## 📁 Project Structure

```text
src/main/java/com/himanshukt/url_shortner
│
├── controller
│   └── UrlController.java
│
├── service
│   ├── UrlService.java
│   └── UrlServiceImpl.java
│
├── repository
│   └── UrlRepository.java
│
├── entity
│   └── UrlMapping.java
│
├── dto
│   ├── CreateShortUrlRequest.java
│   └── CreateShortUrlResponse.java
│
├── exception
│   ├── UrlNotFoundException.java
│   └── GlobalExceptionHandler.java
│
└── UrlShortenerApplication.java
```

---

# 🔌 API Endpoints

## 1. Create Short URL

### Request

```http
POST /api/urls/shorten
```

### Request Body

```json
{
  "originalUrl": "https://www.youtube.com/"
}
```

### Response

```json
{
  "originalUrl": "https://www.youtube.com/",
  "shortUrl": "http://localhost:8080/04AzW"
}
```

---

## 2. Redirect to Original URL

### Request

```http
GET /{shortCode}
```

Example:

```http
GET /04AzW
```

### Flow

```text
Short Code
    ↓
Find URL in database
    ↓
Check whether URL has expired
    ↓
Increment click count
    ↓
Redirect to original URL
```

The application returns an HTTP redirect response to the original URL.

---

# 🗄️ Database Design

The main table is:

```text
url_mapping
```

### Columns

| Column | Description |
|---|---|
| id | Primary key |
| original_url | Original long URL |
| short_code | Generated unique short code |
| created_at | URL creation timestamp |
| expires_at | URL expiration timestamp |
| click_count | Number of times the short URL was accessed |

Conceptually:

```text
url_mapping
--------------------------------
id
original_url
short_code
created_at
expires_at
click_count
```

---

# 🔄 Application Flow

### Creating a Short URL

```text
User
 │
 │ POST /api/urls/shorten
 ▼
Controller
 │
 ▼
Service
 │
 ├── Validate URL
 │
 ├── Generate short code
 │
 ├── Check short-code collision
 │
 ▼
Repository
 │
 ▼
MySQL
 │
 ▼
Short URL returned
```

### Redirecting

```text
User
 │
 │ GET /04AzW
 ▼
Controller
 │
 ▼
Service
 │
 ├── Find short code
 │
 ├── Check expiration
 │
 ├── Increment click count
 │
 ▼
Original URL
 │
 ▼
HTTP Redirect
```

---

# 🛡️ Error Handling

The application handles common errors such as:

- Invalid or empty URL
- Short URL not found
- Expired URL
- Invalid request data
- Short-code collisions

Global exception handling is implemented using:

```java
@RestControllerAdvice
```

---

# ⏳ URL Expiration

URLs can have an expiration timestamp.

Before redirecting, the application checks whether the URL has expired.

If the URL has expired, the request is rejected instead of redirecting the user.

---

# 📊 Click Tracking

Every successful access to a short URL increments its click count.

Example:

```text
click_count = 0

GET /04AzW
       ↓
click_count = 1

GET /04AzW
       ↓
click_count = 2
```

This provides the foundation for future URL analytics.

---

# 🧪 Testing

The APIs can be tested using **Postman**.

### Successful Cases

- Create a valid short URL
- Access a valid short URL
- Verify redirection
- Verify click count
- Verify URL expiration

### Error Cases

- Empty URL
- Invalid request
- Non-existent short code
- Expired URL

---

# ⚙️ Getting Started

## Prerequisites

Make sure you have installed:

- Java 21
- Maven
- MySQL
- Git
- Postman

---

## 1. Clone the Repository

```bash
git clone <YOUR_GITHUB_REPOSITORY_URL>
```

```bash
cd url-shortner
```

---

## 2. Create MySQL Database

Create a database:

```sql
CREATE DATABASE url_shortener;
```

---

## 3. Configure Database

Update your `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/url_shortener
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 4. Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

The application will start at:

```text
http://localhost:8080
```

---

# 📌 Example

### Original URL

```text
https://www.youtube.com/
```

### Generated Short URL

```text
http://localhost:8080/04AzW
```

### Accessing Short URL

```text
http://localhost:8080/04AzW
```

The application finds the corresponding URL and redirects the user to the original URL.

---

# 🔮 Future Enhancements

Possible future improvements:

- User authentication
- Spring Security
- JWT authentication
- User-specific URLs
- URL analytics dashboard
- Redis caching
- Rate limiting
- Custom aliases
- QR code generation
- Docker deployment
- Cloud deployment
- Frontend dashboard

---

# 📚 What I Learned

Through this project, I practiced:

- Spring Boot application development
- REST API design
- DTO pattern
- Layered architecture
- Dependency Injection
- Spring Data JPA
- Hibernate ORM
- Entity mapping
- MySQL integration
- Bean Validation
- Exception handling
- HTTP redirects
- Database persistence
- Git/GitHub workflow

---

## 👨‍💻 Author

**Himanshu**

Java Backend Developer

Built using Java + Spring Boot + MySQL.
