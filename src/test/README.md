# Library Management System - Test Suite

This directory contains comprehensive tests for the Library Management System application.

## Test Structure

### Service Layer Tests
- **BookServicesTest.java** - Tests for book management business logic
- **AuthorServicesTest.java** - Tests for author management business logic
- **MemberServicesTest.java** - Tests for member management business logic
- **ReservationServicesTest.java** - Tests for reservation management business logic
- **BorrowServicesTest.java** - Tests for borrowing and returning books logic
- **ReportServicesTest.java** - Tests for reporting and analytics logic

### Controller Layer Tests
- **BookControllerTest.java** - Tests for book REST endpoints
- **MemberControllerTest.java** - Tests for member REST endpoints
- **AuthorControllerTest.java** - Tests for author REST endpoints
- **BorrowControllerTest.java** - Tests for borrowing REST endpoints
- **ReservationControllerTest.java** - Tests for reservation REST endpoints
- **ReportControllerTest.java** - Tests for reporting REST endpoints

### Utility Classes
- **TestDataBuilder.java** - Helper class for creating test data objects
- **LibraryManagementSystemApplicationTests.java** - Main application context test

### Configuration
- **application-test.properties** - Test-specific configuration

## Test Coverage

### Service Layer Coverage
- ✅ CRUD operations (Create, Read, Update, Delete)
- ✅ Business logic validation
- ✅ Exception handling
- ✅ DTO conversion
- ✅ Repository interaction mocking

### Controller Layer Coverage
- ✅ REST endpoint functionality
- ✅ HTTP status codes
- ✅ Request/response handling
- ✅ Service layer integration
- ✅ Error handling

### Test Scenarios
- ✅ Happy path scenarios
- ✅ Error scenarios
- ✅ Edge cases
- ✅ Null handling
- ✅ Empty result sets

## Running Tests

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=BookServicesTest
```

### Run Tests with Coverage
```bash
mvn test jacoco:report
```

### Run Tests in IDE
- Right-click on test class → Run
- Right-click on test method → Run
- Use IDE's test runner

## Test Dependencies

The tests use the following testing frameworks and libraries:

- **JUnit 5** - Core testing framework
- **Mockito** - Mocking framework for dependencies
- **Spring Boot Test** - Spring Boot testing support
- **MockMvc** - Web layer testing
- **H2 Database** - In-memory database for testing

## Test Data Management

### TestDataBuilder Utility
The `TestDataBuilder` class provides static methods to create test data objects:

```java
// Create test entities
Author author = TestDataBuilder.createTestAuthor();
Book book = TestDataBuilder.createTestBook();
Member member = TestDataBuilder.createTestMember();

// Create test DTOs
BookDTO bookDTO = TestDataBuilder.createTestBookDTO();
MemberDTO memberDTO = TestDataBuilder.createTestMemberDTO();
```

### Test Data Isolation
- Each test method creates its own test data
- Tests are independent and don't share state
- Database is reset between tests using `@DirtiesContext`

## Mocking Strategy

### Service Layer Tests
- Mock repository dependencies
- Test business logic in isolation
- Verify repository method calls

### Controller Layer Tests
- Mock service dependencies
- Test HTTP layer functionality
- Verify service method calls

## Best Practices

### Test Naming
- Use descriptive test method names
- Follow the pattern: `methodName_scenario_expectedResult`
- Example: `addBook_Success`, `getBookById_NotFound`

### Test Structure
- Use Given-When-Then pattern
- Arrange test data in `@BeforeEach`
- Clean up in `@AfterEach` if needed

### Assertions
- Use specific assertions (assertEquals, assertNotNull, etc.)
- Verify mock interactions
- Test both positive and negative scenarios

## Continuous Integration

Tests are automatically run in CI/CD pipelines:
- On every commit
- Before deployment
- Coverage reports generated
- Test results reported

## Troubleshooting

### Common Issues
1. **Test Context Loading Failures**
   - Check test configuration
   - Verify dependencies are available

2. **Mock Setup Issues**
   - Ensure mocks are properly configured
   - Check method signatures match

3. **Database Connection Issues**
   - Verify H2 dependency is included
   - Check test properties configuration

### Debug Mode
Run tests with debug logging:
```bash
mvn test -Dspring.profiles.active=test -Dlogging.level.com.aat=DEBUG
```

## Contributing

When adding new tests:
1. Follow existing naming conventions
2. Use TestDataBuilder for test data
3. Test both success and failure scenarios
4. Maintain high test coverage
5. Document complex test scenarios

## Test Reports

After running tests, reports are generated in:
- **Surefire Reports**: `target/surefire-reports/`
- **Jacoco Coverage**: `target/site/jacoco/`
- **Test Results**: IDE test runner output
