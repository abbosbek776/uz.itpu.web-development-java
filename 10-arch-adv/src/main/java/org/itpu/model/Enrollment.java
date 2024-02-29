package org.itpu.model;

import java.util.Objects;

public class Enrollment {
  private Long enrollmentId;
  private Long studentId;
  private Long courseId;

  public Enrollment(Long enrollmentId, Long studentId, Long courseId) {
    this.enrollmentId = enrollmentId;
    this.studentId = studentId;
    this.courseId = courseId;
  }

  public Enrollment() {
  }

  public Long getEnrollmentId() {
    return enrollmentId;
  }

  public void setEnrollmentId(Long enrollmentId) {
    this.enrollmentId = enrollmentId;
  }

  public Long getStudentId() {
    return studentId;
  }

  public void setStudentId(Long studentId) {
    this.studentId = studentId;
  }

  public Long getCourseId() {
    return courseId;
  }

  public void setCourseId(Long courseId) {
    this.courseId = courseId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Enrollment that = (Enrollment) o;
    return Objects.equals(enrollmentId, that.enrollmentId) && Objects.equals(studentId, that.studentId) &&
        Objects.equals(courseId, that.courseId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(enrollmentId, studentId, courseId);
  }
}
