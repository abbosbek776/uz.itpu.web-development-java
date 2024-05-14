package com.s26springrest.controller;

import com.s26springrest.entity.Employee;
import com.s26springrest.service.EmployeeService;
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
    public List<Employee> all() {
        return employeeService.getAll();
    }
    // end::get-aggregate-root[]

    // Single item
    @GetMapping("/{id}")
    public Employee one(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    @PostMapping
    public Employee newEmployee(@RequestBody Employee newEmployee) {
        return employeeService.saveNewEmployee(newEmployee);
    }

    @PutMapping("/{id}")
    public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return employeeService.updateById(id, newEmployee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
    }

    @DeleteMapping("/name/{firstName}")
    public void deleteEmployee(@PathVariable String firstName) {
        employeeService.deleteByFirstName(firstName);
    }
}
