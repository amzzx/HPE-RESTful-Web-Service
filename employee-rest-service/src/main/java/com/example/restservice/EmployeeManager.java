package com.example.restservice;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EmployeeManager {

    private final Employees employees = new Employees();

    public EmployeeManager() {
        employees.setEmployeeList(List.of(
                new Employee("1", "Alex", "Morgan", "alex.morgan@example.com", "Software Engineer"),
                new Employee("2", "Priya", "Shah", "priya.shah@example.com", "Project Manager"),
                new Employee("3", "Daniel", "Reed", "daniel.reed@example.com", "Business Analyst"),
                new Employee("4", "Sofia", "Chen", "sofia.chen@example.com", "Quality Assurance Engineer")
        ));
    }

    public Employees getAllEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        employees.getEmployeeList().add(employee);
    }
}
