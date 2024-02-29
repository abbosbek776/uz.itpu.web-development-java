package org.itpu.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.itpu.command.Command;
import org.itpu.service.CourseService;
import org.itpu.service.StudentService;


public class FrontController extends HttpServlet {

  private StudentService studentService;
  private CourseService courseService;
  private RequestDispatcher requestDispatcher;

  public FrontController(StudentService studentService, CourseService courseService, RequestDispatcher requestDispatcher) {
    this.studentService = studentService;
    this.courseService = courseService;
    this.requestDispatcher = requestDispatcher;
  }

  public void processRequest(HttpServletRequest request, HttpServletResponse response) {
    String commandType = extractCommandType(request);
    Command command = requestDispatcher.getCommand(commandType);
    command.exexcute(request, response, courseService, studentService);

  }

  private String extractCommandType(HttpServletRequest request) {
    String commandType = request.getParameter("commandType");
    if (commandType == null || commandType.isBlank() || commandType.isEmpty()) {
      commandType = "course";
    }
    return commandType;
  }

}