package com.example.restservice;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employees {

    private List<Employee> employeeList = new CopyOnWriteArrayList<>();

    @JsonProperty("Employees")
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = new CopyOnWriteArrayList<>(employeeList);
    }
}
