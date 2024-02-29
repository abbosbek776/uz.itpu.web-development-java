package org.itpu.service;

import javax.servlet.http.HttpSession;
import org.itpu.model.Course;
import org.itpu.model.Student;

public class StudentService {
  private HttpSession session;

  public StudentService(HttpSession session) {
    this.session = session;
  }

  public void addToCart(Student student, Course course) {

  }
}
