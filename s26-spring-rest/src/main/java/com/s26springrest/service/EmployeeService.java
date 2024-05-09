package com.s26springrest.service;

import com.s26springrest.entity.Employee;
import com.s26springrest.exception.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();
    Employee getById(Long id) throws EmployeeNotFoundException;
    Employee saveNewEmployee(Employee newEmployee);
    Employee updateById(Long id, Employee newEmployee);
    void deleteById(Long id);
    void deleteByName(String name);

}
