# Employee REST API

A Java and Spring Boot project completed for the **Hewlett Packard Enterprise Software Engineering Job Simulation on Forage**. The service retrieves employee records and accepts new employees through a JSON API. It includes **7 unit tests and 5 integration tests**.

## What the project demonstrates

- Two REST operations: retrieve employees with `GET` and create an employee with `POST`.
- JSON request and response mapping with Jackson.
- A controller, service and employee model with separate responsibilities.
- Unit testing with JUnit and Mockito, plus HTTP integration testing with Spring Boot and MockMvc.

The application is a learning project with in-memory storage. It starts with four fictional employees and resets its data when restarted.

## Run locally

Requirements: **JDK 17** and internet access for the first Maven dependency download. The project includes the Maven Wrapper, so a separate Maven installation is not needed. The build configuration uses Spring Boot 4.0.8.

From the repository root on macOS or Linux:

```sh
cd employee-rest-service
./mvnw clean package
java -jar target/employee-rest-service-1.0.0.jar
```

The service listens at `http://localhost:8080`. Stop it with `Ctrl+C`.

On Windows, enter `employee-rest-service`, run `.\mvnw.cmd clean package`, then launch the same JAR with `java -jar target/employee-rest-service-1.0.0.jar`.

## Try the API

Run these commands from the `employee-rest-service` directory in a second terminal while the service is running. On Windows, use `curl.exe` in place of `curl`.

### Retrieve employees

```sh
curl -i http://localhost:8080/employees
```

The response is `200 OK`, with the employee list under the case-sensitive `Employees` property. Each record contains five string fields: `employee_id`, `first_name`, `last_name`, `email` and `title`.

### Add an employee

The repository includes an [example JSON request](employee-rest-service/examples/new-employee.json):

```json
{
  "employee_id": "5",
  "first_name": "Taylor",
  "last_name": "Wilson",
  "email": "taylor.wilson@example.com",
  "title": "Developer"
}
```

```sh
curl -i -X POST http://localhost:8080/employees -H "Content-Type: application/json" --data-binary "@examples/new-employee.json"
```

The response is `201 Created` and contains the added employee. A subsequent `GET /employees` includes that record.

| Method and path | Request body | Response |
| --- | --- | --- |
| `GET /employees` | None | `200 OK`, with the employee list as JSON |
| `POST /employees` | Employee JSON object | `201 Created`, with the added employee as JSON |

## Tests

Run the full suite from the `employee-rest-service` directory:

```sh
./mvnw test
```

| Test class | Count | What it checks |
| --- | --- | --- |
| `EmployeeManagerTests` | 4 unit tests | Initial records, preservation of submitted fields and existing records, repeated retrieval after additions, and independent manager instances |
| `EmployeeControllerUnitTests` | 3 unit tests | Manager delegation, complete and empty employee lists, and the create response using Mockito |
| `EmployeeControllerTests` | 5 integration tests | JSON responses, repeated retrieval, creation followed by retrieval, and rejection of malformed JSON or an empty request body |

The integration tests use the Spring application context and MockMvc. They check that invalid request bodies produce `400 Bad Request` without changing the employee list.

Local validation on 9 October 2026: all 12 tests passed, with no failures, errors or skipped tests, using JDK 23 and the project's Java 17 compilation target.

To run only the seven unit tests:

```sh
./mvnw '-Dtest=EmployeeManagerTests,EmployeeControllerUnitTests' test
```

On Windows, use `.\mvnw.cmd test` for the full suite or `.\mvnw.cmd "-Dtest=EmployeeManagerTests,EmployeeControllerUnitTests" test` for the unit tests. Maven writes execution reports to `employee-rest-service/target/surefire-reports/` relative to the repository root.

## Project structure

The application and build files are in [employee-rest-service/](employee-rest-service/).

| Class | Responsibility |
| --- | --- |
| `EmployeeController` | Maps HTTP requests and delegates employee operations to the manager |
| `EmployeeManager` | Seeds the collection and adds employee records |
| `Employees` | Holds the in-memory list and maps it to the `Employees` JSON property |
| `Employee` | Defines employee fields and JSON mappings |
| `RestServiceApplication` | Starts Spring Boot and the embedded server |

Source files are in `employee-rest-service/src/main/java/com/example/restservice/`; tests are in the corresponding `src/test/java/` package.

## Current scope

The API supports retrieval and creation. It does not include update or delete operations, a database, authentication, duplicate-ID checks, or field-level validation. Employee data is retained only while the application is running.

## Acknowledgements and licence

The Maven setup and application entry point were adapted from the [Spring REST service guide starter](https://github.com/spring-guides/gs-rest-service). The employee service follows the HPE Forage simulation requirements.

The starter's Apache 2.0 licence is retained in [LICENSE.txt](employee-rest-service/LICENSE.txt). The Maven Wrapper files also retain their original Apache licence notices.
