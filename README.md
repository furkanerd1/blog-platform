# Blog Platform API

A modern blog platform REST API developed using Spring Boot. The project includes core blog functionalities such as JWT-based authentication, category management, tagging system, and article management.

## Features

- 🔐 JWT-based authentication
- 📝 Create, edit and delete articles
- 🏷️ Category and tag management
- 📊 Article reading time calculation
- 🔍 Filter articles by category and tags
- 📱 RESTful API design

## Technologies

- Java 17
- Spring Boot 3.4.1
- Spring Security
- Spring Data JPA
- MySQL
- Lombok
- MapStruct
- Validations
- JWT (JSON Web Token)

## Getting Started

### Prerequisites

- Java 17 or higher
- MySQL
- Maven

### Installation
1. Clone the project:
```bash
git clone https://github.com/[your-username]/blog-platform.git
```
2. Create `.env` file and add database credentials:
```bash
application.properties
DB_URL=jdbc:mysql://localhost:3306/blog_db
DB_USERNAME=your_username
DB_PASSWORD=your_password
```
4. Build and run the project:
```bash
mvn spring-boot:run
```
## API Endpoints

### Authentication
- `POST /api/v1/auth` - Login and get JWT token

### Posts
- `GET /api/v1/posts` - List all published posts
- `GET /api/v1/posts/drafts` - List user's draft posts 🔒
- `POST /api/v1/posts` - Create new post 🔒
- `PUT /api/v1/posts/{postId}` - Update post 🔒
- `GET /api/v1/posts/{postId}` - Get post details
- `DELETE /api/v1/posts/{postId}` - Delete post 🔒

### Categories
- `GET /api/v1/categories` - List all categories
- `POST /api/v1/categories` - Create new category 🔒
- `DELETE /api/v1/categories/{categoryId}` - Delete category 🔒

### Tags
- `GET /api/v1/tags` - List all tags
- `POST /api/v1/tags` - Create new tags 🔒
- `DELETE /api/v1/tags/{tagId}` - Delete tag 🔒

Note: 🔒 indicates endpoints that require authentication.
