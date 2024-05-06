package com.example.s25springrest.exception;

public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
