package org.itpu.model;

import java.util.Objects;

public class Student {
  private Long id;
  private String firstName;
  private String familyName;
  private String email;
  private String phoneNumber;
  private String password;

  public Student(String firstName, String familyName, String email, String phoneNumber) {
    this.firstName = firstName;
    this.familyName = familyName;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }

  public Student() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getFamilyName() {
    return familyName;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Student student = (Student) o;
    return Objects.equals(id, student.id) && Objects.equals(firstName, student.firstName) &&
        Objects.equals(familyName, student.familyName) && Objects.equals(email, student.email) &&
        Objects.equals(phoneNumber, student.phoneNumber) && Objects.equals(password, student.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, familyName, email, phoneNumber, password);
  }
}
