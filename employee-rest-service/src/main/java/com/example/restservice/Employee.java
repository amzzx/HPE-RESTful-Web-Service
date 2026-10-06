package com.example.restservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"employee_id", "first_name", "last_name", "email", "title"})
public class Employee {

    private String employee_id;
    private String first_name;
    private String last_name;
    private String email;
    private String title;

    public Employee() {
    }

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

    @JsonProperty("employee_id")
    public void setEmployeeId(String employee_id) {
        this.employee_id = employee_id;
    }

    @JsonProperty("first_name")
    public String getFirstName() {
        return first_name;
    }

    @JsonProperty("first_name")
    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return last_name;
    }

    @JsonProperty("last_name")
    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }
}
