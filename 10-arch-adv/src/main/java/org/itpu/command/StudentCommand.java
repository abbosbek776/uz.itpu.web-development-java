package org.itpu.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.itpu.service.CourseService;
import org.itpu.service.StudentService;

public class StudentCommand implements Command{
  @Override
  public void exexcute(HttpServletRequest request, HttpServletResponse response, CourseService courseService,
      StudentService studentService) {

  }
}
