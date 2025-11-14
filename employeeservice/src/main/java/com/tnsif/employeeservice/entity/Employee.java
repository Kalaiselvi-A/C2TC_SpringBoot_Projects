package com.tnsif.employeeservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity // Marks this class as a JPA entity
@Table(name = "employees") // Maps to the 'employees' table
public class Employee {
    
    // Primary key for Employee table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private Long id;

    // Unique employee code (cannot be blank)
    @NotBlank(message = "Employee code is required")
    @Column(unique = true)
    private String empCode;

    // Employee first name (required)
    @NotBlank(message = "First name is required")
    private String firstName;

    // Employee last name (required)
    @NotBlank(message = "Last name is required")
    private String lastName;

    // Employee department (required)
    @NotBlank(message = "Department is required")
    private String department;

    // Employee designation or job title
    @NotBlank(message = "Designation is required")
    private String designation;

    // Employee email (must be valid and not empty)
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    // Employee salary (must be positive)
    @Positive(message = "Salary must be positive")
    private double salary;

    // Employee phone number (must be 10 digits)
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phoneNumber;

    // Employee address (required)
    @NotBlank(message = "Address is required")
    private String address;

    // City where the employee resides (required)
    @NotBlank(message = "City is required")
    private String city;

    // Default constructor required by JPA
    public Employee() {}

    // Constructor to initialize all fields
    public Employee(Long id, String empCode, String firstName, String lastName,
            String department, String designation, String email, double salary,
            String phoneNumber, String address, String city) 
    {
        this.id = id;
        this.empCode = empCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.designation = designation;
        this.email = email;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
    }
    
    // Returns the employee ID
    public Long getId() {
        return id;
    }

    // Sets the employee ID
    public void setId(Long id) {
        this.id = id;
    }

    // Returns the employee code
    public String getEmpCode() {
        return empCode;
    }

    // Sets the employee code
    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    // Returns the first name
    public String getFirstName() {
        return firstName;
    }

    // Sets the first name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Returns the last name
    public String getLastName() {
        return lastName;
    }

    // Sets the last name
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Returns the department
    public String getDepartment() {
        return department;
    }

    // Sets the department
    public void setDepartment(String department) {
        this.department = department;
    }

    // Returns the designation/job title
    public String getDesignation() {
        return designation;
    }

    // Sets the designation/job title
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    // Returns the email address
    public String getEmail() {
        return email;
    }

    // Sets the email address
    public void setEmail(String email) {
        this.email = email;
    }

    // Returns the salary
    public double getSalary() {
        return salary;
    }

    // Sets the salary
    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Returns the phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Sets the phone number
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Returns the address
    public String getAddress() {
        return address;
    }

    // Sets the address
    public void setAddress(String address) {
        this.address = address;
    }

    // Returns the city
    public String getCity() {
        return city;
    }

    // Sets the city
    public void setCity(String city) {
        this.city = city;
    }
}
