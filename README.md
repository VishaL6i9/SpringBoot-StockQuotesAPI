# ThinkHub Stock Quote Service

## Overview

ThinkHub Stock Quote Service is a Spring Boot application that provides real-time stock quotes. It fetches data from an external API, implements caching for improved performance, and offers robust error handling.

## Features

- Fetch individual stock quotes by symbol
- Retrieve batch quotes for multiple symbols
- Caching mechanism to reduce API calls and improve response time
- Comprehensive error handling and logging
- RESTful API endpoints
- Secure endpoints with basic authentication

## Technology Stack

- Java 22
- Spring Boot 3.3.2
- Spring Security
- Caffeine Cache
- Maven
- Logback for logging
- JUnit and Mockito for testing

## Setup and Installation

1. Ensure you have Java 22 and Maven installed on your system (binary included in project directory).
2. Clone the repository: `git clone https://github.com/VishaL6i9/SpringBoot-StockQuotesAPI.git`
3. Navigate to the project directory: `cd ThinkHub`
4. Build the project: `mvn clean package` 
5. Run the application: `java -jar target/thinkhub-0.0.1-SNAPSHOT.jar` or `./mvnw spring-boot:run`

## Configuration

Key configurations in `application.properties`:

- `server.port`: The port on which the application runs (default: 8080)
- `alphavantage.api.key`: Your Alpha Vantage API key (A working API is being used by default)
- `spring.security.user.name` and `spring.security.user.password`: Credentials for basic auth (vish:6190)
- Cache configurations
- 
**Directory Structure**
========================

Below is an overview of the directory structure for this project:

```
Folder PATH listing
Volume serial number is 789B-4402
C:.
|   .gitignore
|   application.log
|   HELP.md
|   mvnw
|   mvnw.cmd
|   pom.xml
|   readme.md
|   
+---.idea
|       [collapsed]
|
+---.mvn
|       [collapsed]
|
+---src
|   +---main
|   |   +---java
|   |   |   \---vish
|   |   |       \---thinkhub
|   |   |           |   StockQuoteServiceApplication.java
|   |   |           |
|   |   |           +---cache
|   |   |           |       StockQuoteCache.java
|   |   |           |
|   |   |           +---config
|   |   |           |       CacheConfig.java
|   |   |           |       SecurityConfig.java
|   |   |           |       WebClientConfig.java
|   |   |           |
|   |   |           +---Controller
|   |   |           |       StockQuoteController.java
|   |   |           |
|   |   |           +---exception
|   |   |           |       ErrorResponse.java
|   |   |           |       ExternalApiException.java
|   |   |           |       GlobalExceptionHandler.java
|   |   |           |       StockQuoteException.java
|   |   |           |
|   |   |           +---model
|   |   |           |       StockQuote.java
|   |   |           |
|   |   |           \---service
|   |   |               |   ExternalStockApiService.java
|   |   |               |   StockQuoteService.java
|   |   |               |
|   |   |               \---impl
|   |   |                       StockQuoteServiceImpl.java
|   |   |
|   |   \---resources
|   |       |   application-test.properties
|   |       |   application.properties
|   |       |   logback.xml
|   |       |
|   |       +---logs
|   |       |       log.txt
|   |       |
|   |       +---static
|   |       \---templates
|   \---test
|       \---java
    		\---vish
        		\---thinkhub
                	StockQuoteServiceApplicationTests.java

|
\---target
    [collapsed]
```

| Directory | Description |
| --- | --- |
| `.idea` | IntelliJ IDEA configuration files |
| `.mvn` | Maven configuration files |
| `src` | Source code directory |
| `src/main/java` | Java source code directory |
| `src/main/resources` | Resource files (e.g. properties, XML) |
| `src/test/java` | Test source code directory |
| `target` | Build output directory |
## API Endpoints

1. Get quote for a single symbol:
    - GET `/api/v1/stock-quotes/{symbol}`

2. Get quotes for multiple symbols:
    - GET `/api/v1/stock-quotes/batch?symbols=AAPL,GOOGL,MSFT`

3. Get cache statistics:
    - GET `/api/v1/stock-quotes/cache-stats`

## Error Handling

The application uses a global exception handler to manage errors. Common exceptions include:

- `StockQuoteException`: For errors related to stock quote retrieval
- `ExternalApiException`: For errors from the external stock API
- Generic exceptions: For unexpected errors

## Caching

The application uses Caffeine Cache with the following configuration:

- Initial capacity: 100 entries
- Maximum size: 500 entries
- Expiration: Entries expire after 5 minutes

## Logging

Logging is configured using Logback. Log files are stored in `/application.log`.

## Testing

Run tests using:
-  `./mvnw test`
## Docker

Build the Docker image:
- `docker build -t thinkhub-stock-quote-service .`

Run the container:
- `docker run -p 8080:8080 thinkhub-stock-quote-service`
## Contact

For any queries, please contact [vishalkandakatla@gmail.com](mailto:vishalkandakatla@gmail.com).