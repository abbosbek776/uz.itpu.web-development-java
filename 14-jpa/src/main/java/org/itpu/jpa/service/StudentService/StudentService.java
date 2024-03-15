package org.itpu.jpa.service.StudentService;

import org.itpu.jpa.dto.StudentDto;

public interface StudentService {
  // create
  String createStudent(StudentDto studentCreateDto);

  // read
  String getStudent(Long id);

  // update
  StudentDto updateStudent(Long id);

  // delete
  String deleteStudent(Long id);
}
