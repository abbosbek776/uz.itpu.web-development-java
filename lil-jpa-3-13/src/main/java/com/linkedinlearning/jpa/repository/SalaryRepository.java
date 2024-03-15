package com.linkedinlearning.jpa.repository;

import com.linkedinlearning.jpa.entity.Salary;

import java.util.List;
import java.util.Optional;

public interface SalaryRepository {
    Optional<Salary> save(Salary salary);
    Optional<Salary> getSalaryById(Long id);
    void deleteSalary(Salary salary);
}
