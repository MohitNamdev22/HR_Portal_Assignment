package com.hrportal.hr_portal.controller;

import com.hrportal.hr_portal.model.Employee;
import com.hrportal.hr_portal.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Authentication check
    private boolean isAuthenticated(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployees(HttpSession session) {
        if (!isAuthenticated(session)) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Authentication required");
            return ResponseEntity.status(401).body(response);
        }

        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PostMapping
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee, HttpSession session) {
        if (!isAuthenticated(session)) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Authentication required");
            return ResponseEntity.status(401).body(response);
        }

        Employee savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id, HttpSession session) {
        if (!isAuthenticated(session)) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Authentication required");
            return ResponseEntity.status(401).body(response);
        }

        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails, HttpSession session) {
        if (!isAuthenticated(session)) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Authentication required");
            return ResponseEntity.status(401).body(response);
        }

        Optional<Employee> employeeOptional = employeeService.getEmployeeById(id);
        if (!employeeOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Employee employee = employeeOptional.get();
        employee.setName(employeeDetails.getName());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setEmail(employeeDetails.getEmail());
        employee.setSalary(employeeDetails.getSalary());

        Employee updatedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id, HttpSession session) {
        if (!isAuthenticated(session)) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Authentication required");
            return ResponseEntity.status(401).body(response);
        }

        Optional<Employee> employeeOptional = employeeService.getEmployeeById(id);
        if (!employeeOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        employeeService.deleteEmployee(id);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Employee deleted successfully");
        return ResponseEntity.ok(response);
    }
}

