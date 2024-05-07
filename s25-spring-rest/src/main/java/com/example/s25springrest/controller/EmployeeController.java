package com.example.s25springrest.controller;

import com.example.s25springrest.entity.Employee;
import com.example.s25springrest.exception.EmployeeNotFoundException;
import com.example.s25springrest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping
    List<Employee> all() {
        return employeeService.getAll();
    }
    // end::get-aggregate-root[]

    // Single item
    @GetMapping("/{id}")
    Employee one(@PathVariable Long id) throws EmployeeNotFoundException {
        return employeeService.getById(id);
    }

    @PostMapping
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return employeeService.saveNewEmployee(newEmployee);
    }

    @PutMapping("/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return employeeService.updateById(id, newEmployee);
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
    }

    @DeleteMapping("/name/{name}")
    void deleteEmployee(@PathVariable String name) {
        employeeService.deleteByName(name);
    }
}
