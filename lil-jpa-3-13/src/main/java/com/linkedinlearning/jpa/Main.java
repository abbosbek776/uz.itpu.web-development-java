package com.linkedinlearning.jpa;

import com.linkedinlearning.jpa.entity.*;
import com.linkedinlearning.jpa.repository.EmployeeRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {

    @PersistenceContext
    EntityManager entityManager;

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl(entityManager);

        setupData(employeeRepository);

        // get values and print amount
        List<Employee> employees = entityManager.createNativeQuery("select * from employees;", Employee.class).getResultList();
        System.out.println("Amount of employees.size() = " + employees.size());
        List<EmployeeProfile> employeeProfiles = entityManager.createNativeQuery("select * from employee_profiles;", EmployeeProfile.class).getResultList();
        System.out.println("Amount of employeeProfiles.size() = " + employeeProfiles.size());
        List<Salary> salaries = entityManager.createNativeQuery("select * from salaries;", Salary.class).getResultList();
        System.out.println("Amount of salaries.size() = " + salaries.size());

        Long employeeId = employees.get(0).getId();
        Long employeeProfileId = employees.get(0).getProfile().getEmployee().getId();
        Long profileId = employeeProfiles.get(0).getEmployee().getId();
        assert employeeId == employeeProfileId && employeeProfileId == profileId;

        Employee employee1 = entityManager.find(Employee.class, employeeId);
        EmployeeProfile employeeProfile1 = entityManager.find(EmployeeProfile.class, profileId);

        // deleting EMPLOYEE
        employeeRepository.deleteEmployee(employee1);

        // get values and print amount
        // amount of employees is down
        List<Employee> employees9 = entityManager.createNativeQuery("select * from employees;", Employee.class).getResultList();
        System.out.println("Amount of employees9.size() = " + employees9.size());
        // amount of profiles is down (cascade + orphan removal)
        List<EmployeeProfile> employeeProfiles9 = entityManager.createNativeQuery("select * from employee_profiles;", EmployeeProfile.class).getResultList();
        System.out.println("Amount of employeeProfiles9.size() = " + employeeProfiles9.size());
        // amount of salaries is down (cascade + orphan removal)
        List<Salary> salaries9 = entityManager.createNativeQuery("select * from salaries;", Salary.class).getResultList();
        System.out.println("Amount of salaries9.size() = " + salaries9.size());

        entityManager.close();
        entityManagerFactory.close();
    }

    private static void setupData(EmployeeRepositoryImpl employeeRepository) {
        ActiveEmployee employee = new ActiveEmployee();
        employee.setfName("Mary");
        employee.setlName("Johnson");
        employee.setYearsExperience(20);

        ActiveEmployee employee2 = new ActiveEmployee();
        employee2.setfName("John");
        employee2.setlName("Doe");
        employee2.setYearsExperience(5);

        //set employment history
        employee.setCompanies(generateCompanies());
        employee2.setCompanies(generateCompanies());

        //create an EmployeeProfile and associate it to an Employee
        employee.setProfile(new EmployeeProfile("userName", "password!", "email@email.com", employee, "Software Engineer"));
        employee2.setProfile(new EmployeeProfile("jDoe", "password234", "johndoe@email.com", employee2, "Project Manager"));

        //set salaries
        employee.setSalaries(generateSalaries());
        employee2.setSalaries(generateSalaries());

        //save Employee
        employeeRepository.save(employee);
        employeeRepository.save(employee2);
    }

    private static List<Company> generateCompanies() {
        Company company1 = new Company("Google", "USA");
        Company company2 = new Company("Amazon", "USA");

        List<Company> companies = new ArrayList<>();
        companies.add(company1);
        companies.add(company2);

        return companies;
    }

    private static List<Salary> generateSalaries() {
        //create the Salaries and associate to Employee
        Salary currentSalary = new Salary(34000.00, true);
        Salary historicalSalary1 = new Salary(10000.00, false);
        Salary historicalSalary2 = new Salary(5000.00, false);

        List<Salary> salaries = new ArrayList<>();
        salaries.add(currentSalary);
        salaries.add(historicalSalary1);
        salaries.add(historicalSalary2);

        return salaries;
    }

}