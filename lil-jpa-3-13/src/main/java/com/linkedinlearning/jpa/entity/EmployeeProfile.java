package com.linkedinlearning.jpa.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "employee_profiles")
public class EmployeeProfile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="profile_id")
    private Long id;

    @Column
    private String userName;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String title;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public EmployeeProfile() {
    }

    public EmployeeProfile(Long id, String userName, String password, String email, Employee employee, String title) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.employee = employee;
        this.title = title;
    }

    public EmployeeProfile(String userName, String password, String email, Employee employee, String title) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.employee = employee;
        this.title = title;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
