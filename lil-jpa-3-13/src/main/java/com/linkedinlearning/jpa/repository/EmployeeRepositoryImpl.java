package com.linkedinlearning.jpa.repository;

import com.linkedinlearning.jpa.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

//isolate the persistence logic for each entity using the Repository pattern
public class EmployeeRepositoryImpl implements EmployeeRepository {
    EntityManager entityManager;

    public EmployeeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Employee> save(Employee employee) {
        try {
            entityManager.getTransaction().begin(); //uncomment if not using @Transactional
            if (employee.getId() == null) {
                if (employee.getProfile() != null) {
                    entityManager.persist(employee.getProfile());
                }
                entityManager.persist(employee);
            } else {
                employee = entityManager.merge(employee);
            }
            entityManager.getTransaction().commit(); //uncomment if not using @Transactional

            return Optional.of(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, id);
        Optional<Employee> result = employee != null ? Optional.of(employee) : Optional.empty();
        entityManager.getTransaction().commit();
        return result;
    }

    @Override
    public void deleteEmployee(Employee employee) {
        entityManager.getTransaction().begin(); //uncomment if not using @Transactional

        if (entityManager.contains(employee)) {
            entityManager.remove(employee);
        } else {
            entityManager.merge(employee);
        }

        entityManager.getTransaction().commit(); //uncomment if not using @Transactional
    }

}
