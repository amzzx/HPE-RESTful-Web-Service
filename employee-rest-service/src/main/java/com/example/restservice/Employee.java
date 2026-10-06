package com.example.restservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"employee_id", "first_name", "last_name", "email", "title"})
public class Employee {

    private final String employee_id;
    private final String first_name;
    private final String last_name;
    private final String email;
    private final String title;

    public Employee(String employee_id, String first_name, String last_name,
                    String email, String title) {
        this.employee_id = employee_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.title = title;
    }

    @JsonProperty("employee_id")
    public String getEmployeeId() {
        return employee_id;
    }

    @JsonProperty("first_name")
    public String getFirstName() {
        return first_name;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return last_name;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }
}
