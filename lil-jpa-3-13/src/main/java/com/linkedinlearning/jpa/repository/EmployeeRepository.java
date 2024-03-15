package com.linkedinlearning.jpa.repository;

import com.linkedinlearning.jpa.entity.Employee;

import java.util.List;
import java.util.Optional;

//isolate the persistence logic for each entity using the Repository pattern
public interface EmployeeRepository {
    Optional<Employee> save(Employee employee);
    Optional<Employee> getEmployeeById(Long id);
    void deleteEmployee(Employee employee);
}
