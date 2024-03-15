package org.itpu.entity;

import jakarta.persistence.*;

import java.io.Serializable;

/*
employees table:
employee_id BigNum
fName varchar2
lName varchar2
yearsExperience Num

 */
@Entity
@Table(name = "employees")
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

    @OneToOne(mappedBy = "employee")
    private Address address;

    public Employee() {
    }

    public Employee(Long id, String fName, String lName, Integer yearsExperience) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.yearsExperience = yearsExperience;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
