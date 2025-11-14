package com.tnsif.employeeservice.service;

import com.tnsif.employeeservice.entity.Employee;
import java.util.List;

public interface EmployeeService {

    // Saves a new employee into the database
    Employee saveEmployee(Employee employee);

    // Returns a list of all employees
    List<Employee> getAllEmployees();

    // Returns a single employee based on the given ID
    Employee getEmployeeById(Long id);

    // Updates an existing employee using the provided ID and new data
    Employee updateEmployee(Long id, Employee employee);

    // Deletes an employee based on the given ID
    void deleteEmployee(Long id);
    
    //Delete All Records
    void deleteAllEmployees();
}
