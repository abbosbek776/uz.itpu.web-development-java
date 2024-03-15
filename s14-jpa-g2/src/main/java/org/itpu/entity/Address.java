package org.itpu.entity;

import jakarta.persistence.*;

/*
Address table:
address_id
employee_id BigNum
address varchar2
 */

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private Long addressId;

    @Column
    private String address;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Address() {
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
