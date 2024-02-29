package org.itpu.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
  private List<Course> courses = new ArrayList<>();
  public void addCourse(Course course) {
    courses.add(course);
  }

  public void removeCourse(Course course) {
    courses.remove(course);
  }

  public List<Course> getCourses() {
    return courses;
  }

  public void clearCourse() {
    courses.clear();
  }
}
