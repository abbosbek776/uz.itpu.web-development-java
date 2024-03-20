package com.linkedinlearning.jpa;

import com.linkedinlearning.jpa.entity.*;
import com.linkedinlearning.jpa.repository.EmployeeRepositoryImpl;
import com.linkedinlearning.jpa.repository.SalaryRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    @PersistenceContext
    EntityManager entityManager;

    public static void main(String[] args) throws InterruptedException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl(entityManager);
        SalaryRepositoryImpl salaryRepository = new SalaryRepositoryImpl(entityManager);

        setupData(employeeRepository);

        cascadeDeleteEmployeeAndHisSalariesANdProfile(entityManager, employeeRepository);

        System.out.println();
//        salaryRepository.getSalaryById(12L).get().getEmployee();

//        Employee employee = employeeRepository.getEmployeeById(9L).get();
        Salary salary = salaryRepository.getSalaryById(12L).get();
        salaryRepository.deleteSalary(salary);

//        employee = employeeRepository.getEmployeeById(9L).get();

        printCurrentTable(entityManager);
        entityManager.close();
        entityManagerFactory.close();

        TimeUnit.MINUTES.sleep(5);
    }

    private static void cascadeDeleteEmployeeAndHisSalariesANdProfile(EntityManager entityManager, EmployeeRepositoryImpl employeeRepository) {
        printCurrentTable(entityManager);

        // get values and print amount
        List<Employee> employees = entityManager.createNativeQuery("select * from employees;", Employee.class).getResultList();
        List<EmployeeProfile> employeeProfiles = entityManager.createNativeQuery("select * from employee_profiles;", EmployeeProfile.class).getResultList();

        Long employeeId = employees.get(0).getId();
        Long employeeProfileId = employees.get(0).getProfile().getId();
        Long profileId = employeeProfiles.get(0).getId();
        assert employeeId != employeeProfileId && employeeProfileId == profileId;

        Employee employee1 = entityManager.find(Employee.class, employeeId);
        EmployeeProfile employeeProfile1 = entityManager.find(EmployeeProfile.class, profileId);

        // deleting EMPLOYEE
        employeeRepository.deleteEmployee(employee1);

        printCurrentTable(entityManager);

        System.out.println("\nProved that after removing single employee - cascade delete happened");
    }

    private static void printCurrentTable(EntityManager entityManager) {
        List<Employee> employees = entityManager.createNativeQuery("select * from employees;", Employee.class).getResultList();
        System.out.println("Amount of employees.size() = " + employees.size());
        List<EmployeeProfile> employeeProfiles = entityManager.createNativeQuery("select * from employee_profiles;", EmployeeProfile.class).getResultList();
        System.out.println("Amount of employeeProfiles.size() = " + employeeProfiles.size());
        List<Salary> salaries = entityManager.createNativeQuery("select * from salaries;", Salary.class).getResultList();
        System.out.println("Amount of salaries.size() = " + salaries.size());
        List<Company> companies = entityManager.createNativeQuery("select * from companies;", Company.class).getResultList();
        System.out.println("Amount of companies.size() = " + companies.size());
        System.out.println();
    }

    private static void setupData(EmployeeRepositoryImpl employeeRepository) {
        ActiveEmployee employee = new ActiveEmployee();
        employee.setfName("Alfa");
        employee.setlName("Afla");
        employee.setYearsExperience(20);

        ActiveEmployee employee2 = new ActiveEmployee();
        employee2.setfName("Omega");
        employee2.setlName("Agemo");
        employee2.setYearsExperience(5);

        //set employment history
        employee.setCompanies(generateCompanies("USA"));
        employee2.setCompanies(generateCompanies("CPR"));

        //create an EmployeeProfile and associate it to an Employee
        employee.setProfile(new EmployeeProfile("a", "password!a", "alfa@email.com", employee, "Software Engineer"));
        employee2.setProfile(new EmployeeProfile("w", "password!w", "omega@email.com", employee2, "Project Manager"));

        //set salaries
        employee.setSalaries(generateSalaries(1));
        employee2.setSalaries(generateSalaries(2));

        //save Employee
        employeeRepository.save(employee);
        employeeRepository.save(employee2);
    }

    private static List<Company> generateCompanies(String region) {
        Company company1 = new Company("Google " + region, region);
        Company company2 = new Company("Amazon " + region, region);

        List<Company> companies = new ArrayList<>();
        companies.add(company1);
        companies.add(company2);

        return companies;
    }

    private static List<Salary> generateSalaries(int i) {
        //create the Salaries and associate to Employee
        Salary currentSalary = new Salary(1111.00 * i, true);
        Salary historicalSalary1 = new Salary(111.00 * i, false);
        Salary historicalSalary2 = new Salary(11.00 * i, false);

        List<Salary> salaries = new ArrayList<>();
        salaries.add(currentSalary);
        salaries.add(historicalSalary1);
        salaries.add(historicalSalary2);

        return salaries;
    }

}