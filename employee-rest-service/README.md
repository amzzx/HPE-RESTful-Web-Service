# Employee REST Service

A Java Spring Boot application that lists employees with
`GET http://localhost:8080/employees` and adds an employee with
`POST http://localhost:8080/employees`. It starts with four example employees
and stores all employee data in memory.

## Requirements

- JDK 17 is recommended. Set `JAVA_HOME` to your JDK installation if necessary.
- Internet access on the first build to download Maven and the project dependencies.

The project uses Spring Boot 4.0.8 and includes the Maven Wrapper, so a separate
Maven or Gradle installation is not required.

## Build an executable JAR

Open a terminal in the `employee-rest-service` directory.

On macOS or Linux:

```sh
./mvnw clean package
```

On Windows (PowerShell or Command Prompt):

```powershell
.\mvnw.cmd clean package
```

The build runs the automated tests and creates:

```text
target/employee-rest-service-1.0.0.jar
```

If the macOS/Linux wrapper needs execute permission after extraction, run
`chmod +x mvnw` before building.

## Launch the executable

```sh
java -jar target/employee-rest-service-1.0.0.jar
```

Keep this terminal open while using the API. The application listens on port
8080. If that port is already in use, stop the other application first.
Press `Ctrl+C` to stop this service.

Alternatively, run directly from source with `./mvnw spring-boot:run` on macOS/Linux
or `.\mvnw.cmd spring-boot:run` on Windows.

## List employees

Visit [http://localhost:8080/employees](http://localhost:8080/employees) in a browser,
or send an HTTP GET request from a second terminal:

```sh
curl -i http://localhost:8080/employees
```

On Windows, use `curl.exe -i http://localhost:8080/employees`.
The response has HTTP status `200 OK` and content type `application/json`.
Before adding any employees, its body is as follows (whitespace may differ):

```json
{
  "Employees": [
    {
      "employee_id": "1",
      "first_name": "Alex",
      "last_name": "Morgan",
      "email": "alex.morgan@example.com",
      "title": "Software Engineer"
    },
    {
      "employee_id": "2",
      "first_name": "Priya",
      "last_name": "Shah",
      "email": "priya.shah@example.com",
      "title": "Project Manager"
    },
    {
      "employee_id": "3",
      "first_name": "Daniel",
      "last_name": "Reed",
      "email": "daniel.reed@example.com",
      "title": "Business Analyst"
    },
    {
      "employee_id": "4",
      "first_name": "Sofia",
      "last_name": "Chen",
      "email": "sofia.chen@example.com",
      "title": "Quality Assurance Engineer"
    }
  ]
}
```

`Employees` is capitalized exactly as required. Each employee has five string
values, and the JSON field names use the exact spelling from the task.
The example names and email addresses are fictional. To change the examples,
edit the constructor in `EmployeeManager.java` and rebuild.

## Add an employee

Send a single JSON object containing the five string fields below:

```json
{
  "employee_id": "5",
  "first_name": "Taylor",
  "last_name": "Wilson",
  "email": "taylor.wilson@example.com",
  "title": "Developer"
}
```

This example is included in `examples/new-employee.json`. From the project
directory, run this command while the application is running:

```sh
curl -i -X POST http://localhost:8080/employees -H "Content-Type: application/json" --data-binary @examples/new-employee.json
```

On Windows, use:

```powershell
curl.exe -i -X POST http://localhost:8080/employees -H "Content-Type: application/json" --data-binary "@examples/new-employee.json"
```

The response has HTTP status `201 Created` and returns the added employee as a
JSON object. Confirm the new employee is present by requesting the full list:

```sh
curl -i http://localhost:8080/employees
```

On Windows, use `curl.exe -i http://localhost:8080/employees`.
The `Employees` array now contains the original four employees and Taylor
Wilson. Each successful POST adds one employee. Added employees remain available
for subsequent GET requests while the application runs; restarting the
application resets the list to the four original examples.

## Core application files

All five classes are in `src/main/java/com/example/restservice/`:

| File | Responsibility |
| --- | --- |
| `Employee.java` | Stores the five private employee fields and provides getters and setters for JSON requests and responses. |
| `Employees.java` | Maintains the employee list and provides its getter and setter. |
| `EmployeeManager.java` | Initializes the list with four example employees and adds new employees to it. |
| `EmployeeController.java` | Handles `GET /employees` to return the complete list and `POST /employees` to add an employee. |
| `RestServiceApplication.java` | Starts the Spring Boot application and embedded server. |

| Method and endpoint | Request body | Response |
| --- | --- | --- |
| `GET /employees` | None | `200 OK` with the complete list under the `Employees` property. |
| `POST /employees` | One employee JSON object with the five fields shown above. | `201 Created` with the added employee JSON object. |

No database or external server setup is needed.

## Automated tests

```sh
./mvnw test
```

On Windows, use `.\mvnw.cmd test`.

The five tests in `EmployeeControllerTests` check the exact initial JSON response,
repeated GET requests, and that POST returns `201 Created`, preserves the supplied
string employee ID, and includes the new employee in subsequent GET responses.
They also verify that malformed JSON and an empty request body return `400 Bad
Request` without changing the employee list.

## Submission

The supplied `employee-rest-service.zip` contains this runnable source project,
including the five required Java files, this README, the build configuration,
the Maven Wrapper, tests, and the example POST request JSON file. Extract it before
building. Build output and local dependency caches are excluded from the ZIP.

## Reference resources

The Maven setup and application entry point are adapted from the starter in
[spring-guides/gs-rest-service](https://github.com/spring-guides/gs-rest-service).
The employee classes implement the requirements in the supplied screenshots.

- [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
- [How to Create a REST API using Java Spring Boot](https://www.geeksforgeeks.org/java/how-to-create-a-rest-api-using-java-spring-boot/)
- [Download and Upload Files with Spring Boot](https://devwithus.com/download-upload-files-with-spring-boot/)
- [Creating a RESTful HTTP Server in Spring Boot (Java)](https://www.sohamkamani.com/java/spring-rest-http-server/)

The included `LICENSE.txt` preserves the Spring guide starter's Apache 2.0 license.
