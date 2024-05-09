package com.s26springrest.service;

import com.s26springrest.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();
    Employee getById(Long id);
    Employee saveNewEmployee(Employee newEmployee);
    Employee updateById(Long id, Employee newEmployee);
    void deleteById(Long id);
    void deleteByName(String name);

}
