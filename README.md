# Library Management System API

A comprehensive Spring Boot application for managing library operations including books, members, borrowing, reservations, and reporting.

## Features

- **Book Management**: CRUD operations for books with author, publisher, and genre support
- **Member Management**: Member registration and management
- **Borrowing System**: Book borrowing and return functionality
- **Reservation System**: Book reservation queue management
- **Reporting**: Comprehensive library statistics and analytics
- **Author Management**: Author information and biography management

## API Endpoints

### Books API (`/api/books`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/books` | Add a new book |
| GET | `/api/books/{id}` | Get book by ID |
| GET | `/api/books` | Get all books |
| PUT | `/api/books/{id}` | Update book by ID |
| DELETE | `/api/books/{id}` | Delete book by ID |
| GET | `/api/books/search?title={title}` | Search books by title |
| GET | `/api/books/{id}/available-copies` | Get available copies count |

### Authors API (`/api/authors`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/authors` | Create a new author |
| GET | `/api/authors/{id}` | Get author by ID |
| GET | `/api/authors` | Get all authors |
| PUT | `/api/authors/{id}` | Update author by ID |
| DELETE | `/api/authors/{id}` | Delete author by ID |
| GET | `/api/authors/search?name={name}` | Search authors by name |

### Members API (`/api/members`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/members/register` | Register a new member |
| GET | `/api/members/{id}` | Get member by ID |
| GET | `/api/members` | Get all members |
| PUT | `/api/members/{id}` | Update member by ID |
| DELETE | `/api/members/{id}` | Delete member by ID |
| GET | `/api/members/{id}/borrow-history` | Get member's borrow history |

### Borrowing API (`/api/borrows`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/borrows/{memberId}/books/{bookId}` | Borrow a book |
| POST | `/api/borrows/return?barcode={barcode}` | Return a book by barcode |
| GET | `/api/borrows/{id}` | Get borrow record by ID |
| GET | `/api/borrows` | Get all borrow records |

### Reservations API (`/api/reservations`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/reservations/{memberId}/books/{bookId}` | Create a reservation |
| GET | `/api/reservations/books/{bookId}/next` | Get next reservation for a book |
| PUT | `/api/reservations/{id}/notify` | Mark reservation as notified |
| DELETE | `/api/reservations/{id}` | Cancel a reservation |
| GET | `/api/reservations/{id}` | Get reservation by ID |
| GET | `/api/reservations` | Get all reservations |

### Reports API (`/api/reports`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/reports/dashboard` | Get dashboard summary report |
| GET | `/api/reports/top-genres?limit={limit}` | Get top genres by borrow count |
| GET | `/api/reports/top-authors?limit={limit}` | Get top authors by borrow count |
| GET | `/api/reports/most-borrowed-books?limit={limit}` | Get most borrowed books |
| GET | `/api/reports/member-statistics` | Get member statistics |

## Data Transfer Objects (DTOs)

### BookDTO
```json
{
  "id": 1,
  "title": "Sample Book",
  "isbn": "978-0-123456-47-2",
  "publisherName": "Sample Publisher",
  "authorNames": ["Author 1", "Author 2"],
  "genreName": "Fiction"
}
```

### AuthorDTO
```json
{
  "id": 1,
  "name": "Author Name",
  "biography": "Author biography text"
}
```

### MemberDTO
```json
{
  "id": 1,
  "name": "Member Name",
  "email": "member@example.com",
  "membershipType": "REGULAR"
}
```

### BorrowRecordDTO
```json
{
  "id": 1,
  "bookTitle": "Sample Book",
  "memberName": "Member Name",
  "borrowDate": "2024-01-15",
  "dueDate": "2024-01-29",
  "returnDate": null
}
```

### ReservationDTO
```json
{
  "id": 1,
  "bookTitle": "Sample Book",
  "memberName": "Member Name",
  "reservationDate": "2024-01-15",
  "status": "QUEUED"
}
```

### ReportDTO
```json
{
  "mostBorrowedBook": "Sample Book",
  "topMember": "Member Name",
  "activeReservations": 5,
  "totalBooks": 100
}
```

## Business Rules

1. **Borrowing Limits**: Members can borrow up to 5 books simultaneously
2. **Loan Period**: Books are loaned for 14 days by default
3. **Reservation Queue**: Books can be reserved when all copies are borrowed
4. **Copy Management**: Each book can have multiple inventory copies
5. **Status Tracking**: Books and copies have status tracking (AVAILABLE, BORROWED, RESERVED)

## Error Handling

The API uses proper HTTP status codes and includes comprehensive error handling:

- `400 Bad Request`: Invalid input data or business rule violations
- `404 Not Found`: Resource not found
- `500 Internal Server Error`: Server-side errors

## Technologies Used

- **Spring Boot**: Main framework
- **Spring Data JPA**: Data persistence
- **Lombok**: Code generation and boilerplate reduction
- **H2/MySQL**: Database (configurable)
- **Maven**: Build tool

## Getting Started

1. Clone the repository
2. Configure database connection in `application.properties`
3. Run `mvn spring-boot:run`
4. Access the API at `http://localhost:8080`

## Configuration

Update `application.properties` for your database configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

## Testing

Use the provided endpoints with tools like:
- Postman
- cURL
- Any REST client

Example cURL commands:

```bash
# Get all books
curl -X GET http://localhost:8080/api/books

# Create a new book
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{"title":"Sample Book","isbn":"978-0-123456-47-2"}'

# Borrow a book
curl -X POST http://localhost:8080/api/borrows/1/books/1
```
