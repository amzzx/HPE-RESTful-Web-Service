# Employee REST Service

A Java Spring Boot application that returns four example employees from
`GET http://localhost:8080/employees`. The employee data is hard-coded in memory.

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

## Test the endpoint

Visit [http://localhost:8080/employees](http://localhost:8080/employees) in a browser,
or send an HTTP GET request from a second terminal:

```sh
curl -i http://localhost:8080/employees
```

On Windows, use `curl.exe -i http://localhost:8080/employees`.
The response has HTTP status `200 OK`, content type `application/json`, and this
body (whitespace may differ):

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

## Core application files

All five classes are in `src/main/java/com/example/restservice/`:

| File | Responsibility |
| --- | --- |
| `Employee.java` | Stores the five private employee fields and provides their getters. |
| `Employees.java` | Maintains the employee list and provides its getter and setter. |
| `EmployeeManager.java` | Initializes the list with four example employees. |
| `EmployeeController.java` | Handles `GET /employees` and returns the complete list as JSON. |
| `RestServiceApplication.java` | Starts the Spring Boot application and embedded server. |

The only application endpoint is `GET /employees`. Adding employees through
HTTP is outside this task; a POST request to `/employees` returns `405 Method Not
Allowed`. No database or external server setup is needed.

## Automated tests

```sh
./mvnw test
```

On Windows, use `.\mvnw.cmd test`.

`EmployeeControllerTests` checks the complete JSON response, repeated GET
requests, and rejection of POST requests without changing the employee data.

## Submission

The supplied `employee-rest-service.zip` contains this runnable source project,
including the five required Java files, this README, the build configuration,
the Maven Wrapper, and tests. Extract it before building. Build output and local
dependency caches are excluded from the ZIP.

## Reference resources

The Maven setup and application entry point are adapted from the starter in
[spring-guides/gs-rest-service](https://github.com/spring-guides/gs-rest-service).
The employee classes implement the requirements in the supplied screenshots.

- [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
- [How to Create a REST API using Java Spring Boot](https://www.geeksforgeeks.org/java/how-to-create-a-rest-api-using-java-spring-boot/)

The included `LICENSE.txt` preserves the Spring guide starter's Apache 2.0 license.
