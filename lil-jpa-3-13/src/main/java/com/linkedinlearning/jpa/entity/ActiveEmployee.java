package com.linkedinlearning.jpa.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ActiveEmployee extends Employee {

    @Column
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Salary> salaries = new ArrayList<>();

    @Transient
    private Double totalCompensation;

    public ActiveEmployee() {
    }

    public ActiveEmployee(List<Salary> salaries, Double totalCompensation) {
        this.salaries = salaries;
        this.totalCompensation = totalCompensation;
    }

    public ActiveEmployee(Long id, String fName, String lName, Integer yearsExperience, List<Company> companies, List<Salary> salaries, Double totalCompensation) {
        super(id, fName, lName, yearsExperience, companies);
        this.salaries = salaries;
        this.totalCompensation = totalCompensation;
    }

    public List<Salary> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<Salary> salaries) {
        this.salaries = salaries;
    }

    public Double getTotalCompensation() {
        return totalCompensation;
    }

    public void setTotalCompensation(Double totalCompensation) {
        this.totalCompensation = totalCompensation;
    }

    public void addSalary(Salary salary) {
        salaries.add(salary);
    }
}
