package com.linkedinlearning.jpa.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy=InheritanceType.JOINED)
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="employee_id")
    private Long id;

    @Column
    private String fName;

    @Column
    private String lName;

    @Column
    private Integer yearsExperience;

    @Transient
    private Double totalCompensation;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "employee_company",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id")
    )
    private List<Company> companies = new ArrayList<>();

    @OneToOne(mappedBy="employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private EmployeeProfile profile;

    @Column
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "employee_id")
    private List<Salary> salaries = new ArrayList<>();

    public Employee() {
    }

    public Employee(Long id, String fName, String lName, Integer yearsExperience, List<Company> companies, List<Salary> salaries) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.yearsExperience = yearsExperience;
        this.companies = companies;
        this.salaries = salaries;
    }

    public Employee(Long id, String fName, String lName, Integer yearsExperience, List<Company> companies) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.yearsExperience = yearsExperience;
        this.companies = companies;
    }

    public Long getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public Integer getYearsExperience() {
        return yearsExperience;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setYearsExperience(Integer yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

    public Double getTotalCompensation() {
        return totalCompensation;
    }

    public void setTotalCompensation(Double totalCompensation) {
        this.totalCompensation = totalCompensation;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public EmployeeProfile getProfile() {
        return profile;
    }

    public void setProfile(EmployeeProfile profile) {
        this.profile = profile;
    }

    public List<Salary> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<Salary> salaries) {
        this.salaries = salaries;
    }
}
