package com.s26springrest.controller;

import com.s26springrest.entity.Employee;
import com.s26springrest.hateoas.EmployeeModelAssembler;
import com.s26springrest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("employees/v2")
public class EmployeeControllerV2 {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeModelAssembler employeeModelAssembler;

    @GetMapping("/{id}")
    public EntityModel<Employee> one(@PathVariable Long id) {

        Employee employee = employeeService.getById(id);

        return EntityModel.of(employee,
                linkTo(methodOn(EmployeeControllerV2.class).one(id)).withSelfRel(),
                linkTo(methodOn(EmployeeControllerV2.class).all()).withRel("employees"));
    }

    @GetMapping
    public CollectionModel<EntityModel<Employee>> all() {

        List<EntityModel<Employee>> employees = employeeService.getAll().stream()
                .map(employee -> EntityModel.of(employee,
                        linkTo(methodOn(EmployeeControllerV2.class).one(employee.getId())).withSelfRel(),
                        linkTo(methodOn(EmployeeControllerV2.class).all()).withRel("employees")))
                .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(EmployeeControllerV2.class).all()).withSelfRel());
    }

}
