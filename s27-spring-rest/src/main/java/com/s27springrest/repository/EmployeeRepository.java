package com.s27springrest.repository;

import com.s27springrest.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    void deleteByFirstName(String firstName);
}
