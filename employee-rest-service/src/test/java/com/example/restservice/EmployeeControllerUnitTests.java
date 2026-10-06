package com.example.restservice;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerUnitTests {

    @Mock
    private EmployeeManager employeeManager;

    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        employeeController = new EmployeeController(employeeManager);
    }

    @Test
    void getEmployeesReturnsAllEmployeesProvidedByTheManager() {
        List<Employee> expectedList = List.of(
                new Employee("1", "Alex", "Morgan", "alex.morgan@example.com", "Software Engineer"),
                new Employee("2", "Priya", "Shah", "priya.shah@example.com", "Project Manager")
        );
        Employees expectedEmployees = new Employees();
        expectedEmployees.setEmployeeList(expectedList);
        when(employeeManager.getAllEmployees()).thenReturn(expectedEmployees);

        Employees result = employeeController.getEmployees();

        assertSame(expectedEmployees, result);
        assertIterableEquals(expectedList, result.getEmployeeList());
        verify(employeeManager).getAllEmployees();
        verifyNoMoreInteractions(employeeManager);
    }

    @Test
    void getEmployeesSupportsAnEmptyEmployeeList() {
        Employees emptyEmployees = new Employees();
        emptyEmployees.setEmployeeList(List.of());
        when(employeeManager.getAllEmployees()).thenReturn(emptyEmployees);

        Employees result = employeeController.getEmployees();

        assertSame(emptyEmployees, result);
        assertTrue(result.getEmployeeList().isEmpty());
        verify(employeeManager).getAllEmployees();
        verifyNoMoreInteractions(employeeManager);
    }

    @Test
    void addEmployeePassesTheEmployeeToTheManagerAndReturnsCreated() {
        Employee employee = new Employee(
                "employee-005", "Taylor", "Wilson", "taylor.wilson@example.com", "Developer"
        );

        ResponseEntity<Employee> response = employeeController.addEmployee(employee);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertSame(employee, response.getBody());
        verify(employeeManager).addEmployee(employee);
        verifyNoMoreInteractions(employeeManager);
    }
}
