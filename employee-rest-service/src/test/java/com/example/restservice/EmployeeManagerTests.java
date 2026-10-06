package com.example.restservice;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class EmployeeManagerTests {

    private EmployeeManager employeeManager;

    @BeforeEach
    void setUp() {
        employeeManager = new EmployeeManager();
    }

    @Test
    void startsWithTheFourExampleEmployees() {
        List<Employee> employees = employeeManager.getAllEmployees().getEmployeeList();

        assertEquals(4, employees.size());
        assertEquals(List.of("1", "2", "3", "4"),
                employees.stream().map(Employee::getEmployeeId).toList());
    }

    @Test
    void addingAnEmployeeRetainsExistingEmployeesAndAllSubmittedFields() {
        List<Employee> originals = List.copyOf(employeeManager.getAllEmployees().getEmployeeList());
        Employee newEmployee = new Employee(
                "employee-005", "Taylor", "Wilson", "taylor.wilson@example.com", "Developer"
        );

        employeeManager.addEmployee(newEmployee);

        List<Employee> employees = employeeManager.getAllEmployees().getEmployeeList();
        assertEquals(5, employees.size());
        assertIterableEquals(originals, employees.subList(0, originals.size()));
        Employee added = employees.get(4);
        assertEquals("employee-005", added.getEmployeeId());
        assertEquals("Taylor", added.getFirstName());
        assertEquals("Wilson", added.getLastName());
        assertEquals("taylor.wilson@example.com", added.getEmail());
        assertEquals("Developer", added.getTitle());
    }

    @Test
    void multipleAdditionsRemainAvailableInLaterQueries() {
        List<Employee> expected = new ArrayList<>(employeeManager.getAllEmployees().getEmployeeList());
        Employee first = new Employee(
                "employee-005", "Taylor", "Wilson", "taylor.wilson@example.com", "Developer"
        );
        Employee second = new Employee(
                "employee-006", "Jamie", "Patel", "jamie.patel@example.com", "Designer"
        );
        expected.add(first);
        expected.add(second);

        employeeManager.addEmployee(first);
        employeeManager.addEmployee(second);

        assertIterableEquals(expected, employeeManager.getAllEmployees().getEmployeeList());
        assertIterableEquals(expected, employeeManager.getAllEmployees().getEmployeeList());
    }

    @Test
    void separateManagersDoNotShareAddedEmployeeData() {
        EmployeeManager anotherManager = new EmployeeManager();
        Employee newEmployee = new Employee(
                "employee-005", "Taylor", "Wilson", "taylor.wilson@example.com", "Developer"
        );

        employeeManager.addEmployee(newEmployee);

        assertEquals(5, employeeManager.getAllEmployees().getEmployeeList().size());
        assertEquals(4, anotherManager.getAllEmployees().getEmployeeList().size());
        assertFalse(anotherManager.getAllEmployees().getEmployeeList().stream()
                .anyMatch(employee -> "employee-005".equals(employee.getEmployeeId())));
    }
}
