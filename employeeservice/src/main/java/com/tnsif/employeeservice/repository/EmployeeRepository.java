package com.tnsif.employeeservice.repository;

import com.tnsif.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //1. AUTOMATIC CUSTOM QUERIES (Derived Queries)

    // Finds employees belonging to a specific department
    List<Employee> findByDepartment(String department);

    // Finds employees living in a specific city
    List<Employee> findByCity(String city);

    // Finds employees with a specific designation
    List<Employee> findByDesignation(String designation);

    // Finds employees whose salary is greater than the given value
    List<Employee> findBySalaryGreaterThan(double salary);

    // Finds employees whose salary is between two values
    List<Employee> findBySalaryBetween(double min, double max);

    // Finds employees whose first name contains a given keyword (case-insensitive)
    List<Employee> findByFirstNameContainingIgnoreCase(String keyword);

    // Finds employees whose email ends with a specific domain (e.g., "@gmail.com")
    List<Employee> findByEmailEndingWith(String domain);

    // Finds employees based on both department and city
    List<Employee> findByDepartmentAndCity(String department, String city);


    //2. MANUAL JPQL QUERIES

    // Returns all employees ordered by highest salary first - Sorting (order by salary)
    @Query("SELECT e FROM Employee e ORDER BY e.salary DESC")
    List<Employee> findAllOrderBySalaryDesc();

    // Returns employees who live in the given city (using named parameter)
    @Query("SELECT e FROM Employee e WHERE e.city = :city")
    List<Employee> findEmployeesByCity(@Param("city") String city);

    // Returns employees whose first name starts with the given prefix
    @Query("SELECT e FROM Employee e WHERE LOWER(e.firstName) LIKE LOWER(CONCAT(:prefix, '%'))")
    List<Employee> findByNameStartsWith(@Param("prefix") String prefix);

    // Returns employees who earn more than given salary AND belong to a specific department
    @Query("SELECT e FROM Employee e WHERE e.salary > :salary AND e.department = :department")
    List<Employee> findHighEarnersInDepartment(@Param("salary") double salary,
                                               @Param("department") String department);

    // Returns average salary for each department
    @Query("SELECT e.department, AVG(e.salary) FROM Employee e GROUP BY e.department")
    List<Object[]> getAverageSalaryByDepartment();

    // Returns count of employees grouped by city
    @Query("SELECT e.city, COUNT(e) FROM Employee e GROUP BY e.city")
    List<Object[]> countEmployeesByCity();

    // Returns the top 3 highest-paid employees
    @Query("SELECT e FROM Employee e ORDER BY e.salary DESC LIMIT 3")
    List<Employee> findTop3HighestPaid();

    // Finds employees who have salary greater than the given value (positional parameter)
    @Query("SELECT e FROM Employee e WHERE e.salary > ?1")
    List<Employee> findEmployeesWithHighSalary(double salary);

    // Finds employees based on city and designation using positional parameters
    @Query("SELECT e FROM Employee e WHERE e.city = ?1 AND e.designation = ?2")
    List<Employee> findByCityAndDesignation(String city, String designation);
}
