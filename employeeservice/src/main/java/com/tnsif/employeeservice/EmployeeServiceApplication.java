package com.tnsif.employeeservice;

import com.tnsif.employeeservice.entity.Employee;
import com.tnsif.employeeservice.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeServiceApplication.class, args);
    }

    // 🌟 Auto-load sample data when the app starts
    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository) {
    	// CommandLineRunner bean executes after application startup
        // EmployeeRepository is injected automatically by Spring
    	
        return args -> {
            if (employeeRepository.count() == 0) {  // insert only if table is empty
                System.out.println("🔹 Inserting sample employees into database...");

                employeeRepository.save(new Employee(null, "EMP101", "Kalai", "Selvi",
                        "IT", "Developer", "kalai@example.com", 65000,
                        "9876543210", "12, Anna Nagar", "Chennai"));

                employeeRepository.save(new Employee(null, "EMP102", "Poojitha", "R",
                        "Finance", "Analyst", "poojitha@corp.com", 58000,
                        "9123456789", "44, Gandhi St", "Bangalore"));

                employeeRepository.save(new Employee(null, "EMP103", "Swathi", "S",
                        "HR", "HR Executive", "swathi@corp.com", 50000,
                        "9786543210", "21, Lake View", "Hyderabad"));

                employeeRepository.save(new Employee(null, "EMP104", "Dhivyadharshini", "M",
                        "Marketing", "Coordinator", "dhivya@corp.com", 52000,
                        "8899776655", "7, MG Road", "Pune"));

                employeeRepository.save(new Employee(null, "EMP105", "Srivarshini", "A",
                        "IT", "Tester", "srivarshini@corp.com", 60000,
                        "9090888777", "9, Tech Park", "Chennai"));

                System.out.println("✅ Sample employees added successfully!");
            } else {
                System.out.println("⚙️ Database already contains employee records.");
            }
        };
    }
}

