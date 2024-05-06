package com.example.s25springrest.service;

import com.example.s25springrest.entity.Employee;
import com.example.s25springrest.exception.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();
    Employee getById(Long id) throws EmployeeNotFoundException;
    Employee saveNewEmployee(Employee newEmployee);
    Employee updateById(Long id, Employee newEmployee);
    void deleteById(Long id);

}
