// Service-Layer Implementation
package com.tnsif.employeeservice.service;

import com.tnsif.employeeservice.entity.Employee;
import com.tnsif.employeeservice.exception.ResourceNotFoundException;
import com.tnsif.employeeservice.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Marks this class as a Spring service component
public class EmployeeServiceImpl implements EmployeeService {

    // Repository used to perform CRUD operations on Employee entity
    private final EmployeeRepository employeeRepository;

    // Constructor-based dependency injection of repository
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Saves a new employee or updates an existing employee
    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Returns a list of all employees stored in the database
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Returns an employee by ID, or throws exception if not found
    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));
    }

    // Updates an employee's details using the provided ID and new data
    @Override
    public Employee updateEmployee(Long id, Employee updated) {
        // Fetch existing employee or throw exception
        Employee employee = getEmployeeById(id);

        // Update fields with new values
        employee.setFirstName(updated.getFirstName());
        employee.setLastName(updated.getLastName());
        employee.setDepartment(updated.getDepartment());
        employee.setDesignation(updated.getDesignation());
        employee.setSalary(updated.getSalary());
        employee.setEmail(updated.getEmail());
        employee.setPhoneNumber(updated.getPhoneNumber());
        employee.setAddress(updated.getAddress());
        employee.setCity(updated.getCity());

        // Save updated employee back into database
        return employeeRepository.save(employee);
    }

    // Deletes an employee from the database using the given ID
    @Override
    public void deleteEmployee(Long id) {
//    	Employee employee = getEmployeeById(id);
//    	employeeRepository.delete(employee);
    	
    	//ID does NOT exist:No exception is thrown.
        employeeRepository.deleteById(id);
    }
    
    // Delete all employees record from database
    @Override
    public void deleteAllEmployees() {
        employeeRepository.deleteAll();
    }

}
