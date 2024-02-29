package org.itpu.model;

import java.util.Objects;

public class Course {
  private Long id;
  private String name;
  private String description;
  private Double price;

  public Course(String name, String description, Double price) {
    this.name = name;
    this.description = description;
    this.price = price;
  }

  public Course() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Course course = (Course) o;
    return Objects.equals(getId(), course.getId()) && Objects.equals(getName(), course.getName()) &&
        Objects.equals(getDescription(), course.getDescription()) && Objects.equals(getPrice(), course.getPrice());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getDescription(), getPrice());
  }
}
