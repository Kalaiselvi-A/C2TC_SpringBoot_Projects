package com.tnsif.employeeservice.controller;

import com.tnsif.employeeservice.entity.Employee;
import com.tnsif.employeeservice.repository.EmployeeRepository;
import com.tnsif.employeeservice.service.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    // Service layer object for business logic
    private final EmployeeService employeeService;

    // Repository object for calling JPQL & custom queries
    private final EmployeeRepository employeeRepository;

    // Constructor Injection (Recommended)
    public EmployeeController(EmployeeService employeeService, EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }

    // ======================================================================
    // CRUD ENDPOINTS
    // ======================================================================

    // Create a new employee
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);	//201
    }

    // Get all employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);		//200
    }

    // Get employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    // Update employee by ID
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.updateEmployee(id, employee), HttpStatus.OK);
    }

    // Delete employee by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee deleted successfully!", HttpStatus.OK);
    }
    
    // Delete all employees
    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteAllEmployees() {
        employeeService.deleteAllEmployees();
        return ResponseEntity.ok("All employees deleted successfully.");
    }


    // ======================================================================
    // AUTOMATIC CUSTOM QUERY ENDPOINTS (Derived Queries)
    // ======================================================================

    // Find employees by city
    @GetMapping("/city/{city}")
    public ResponseEntity<List<Employee>> getEmployeesByCity(@PathVariable String city) {
        List<Employee> employees = employeeRepository.findByCity(city);
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);		//204
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Find employees by designation
    @GetMapping("/designation/{designation}")
    public ResponseEntity<List<Employee>> getByDesignation(@PathVariable String designation) {
        List<Employee> employees = employeeRepository.findByDesignation(designation);
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Find employees between salary range
    @GetMapping("/salary-between/{min}/{max}")
    public ResponseEntity<List<Employee>> getBySalaryRange(@PathVariable double min, @PathVariable double max) {
        List<Employee> employees = employeeRepository.findBySalaryBetween(min, max);
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Search employees by name keyword
    @GetMapping("/name-search/{keyword}")
    public ResponseEntity<List<Employee>> searchByName(@PathVariable String keyword) {
        List<Employee> employees = employeeRepository.findByFirstNameContainingIgnoreCase(keyword);
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // ======================================================================
    // MANUAL JPQL QUERY ENDPOINTS
    // ======================================================================

    // Get all employees ordered by salary descending
    @GetMapping("/salary-desc")
    public ResponseEntity<List<Employee>> getAllOrderBySalaryDesc() {
        List<Employee> employees = employeeRepository.findAllOrderBySalaryDesc();
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Get average salary by department
    @GetMapping("/average-salary")
    public ResponseEntity<List<Object[]>> getAverageSalaryByDepartment() {
        List<Object[]> employees = employeeRepository.getAverageSalaryByDepartment();
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Count employees by city
    @GetMapping("/count-by-city")
    public ResponseEntity<List<Object[]>> countEmployeesByCity() {
        List<Object[]> employees = employeeRepository.countEmployeesByCity();
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Find employees whose name starts with prefix
    @GetMapping("/name-prefix/{prefix}")
    public ResponseEntity<List<Employee>> getByNamePrefix(@PathVariable String prefix) {
        List<Employee> employees = employeeRepository.findByNameStartsWith(prefix);
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Find high earners in a department (salary > input)
    @GetMapping("/high-earners/{department}/{salary}")
    public ResponseEntity<List<Employee>> getHighEarnersByDept(
            @PathVariable String department,
            @PathVariable double salary) {
        List<Employee> employees = employeeRepository.findHighEarnersInDepartment(salary, department);
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // ======================================================================
    // EXTRA QUERY METHODS
    // ======================================================================

    // Find employees by department
    @GetMapping("/department/{department}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable String department) {
        List<Employee> employees = employeeRepository.findByDepartment(department);
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Find employees with salary above a certain amount
    @GetMapping("/salary-above/{amount}")
    public ResponseEntity<List<Employee>> getEmployeesWithHighSalary(@PathVariable double amount) {
        List<Employee> employees = employeeRepository.findEmployeesWithHighSalary(amount);
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Find employees by both city and designation
    @GetMapping("/city/{city}/designation/{designation}")
    public ResponseEntity<List<Employee>> getByCityAndDesignation(
            @PathVariable String city,
            @PathVariable String designation) {
        List<Employee> employees = employeeRepository.findByCityAndDesignation(city, designation);
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
