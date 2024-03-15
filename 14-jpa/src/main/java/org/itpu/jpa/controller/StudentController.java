package org.itpu.jpa.controller;

import org.itpu.jpa.dto.StudentDto;
import org.itpu.jpa.service.StudentService.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @PostMapping
  public String createStudent(@RequestBody StudentDto studentDto) {
    return studentService.createStudent(studentDto);
  }

  @GetMapping("/{id}")
  public String getStudent(@PathVariable Long id) {
    return studentService.getStudent(id);
  }


}
