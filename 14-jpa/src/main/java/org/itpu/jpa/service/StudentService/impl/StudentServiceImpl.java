package org.itpu.jpa.service.StudentService.impl;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.itpu.jpa.domain.Student;
import org.itpu.jpa.dto.StudentDto;
import org.itpu.jpa.repository.StudentRepository;
import org.itpu.jpa.service.StudentService.StudentService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

//  @Autowired
//  private EntityManager entityManager;

  private final StudentRepository repository;

  public StudentServiceImpl(StudentRepository repository) {
    this.repository = repository;
  }


  @Override
  public String createStudent(StudentDto studentCreateDto) {
    Student student = new Student();
    student.setFirstName(studentCreateDto.getFirstName());
    student.setLastName(studentCreateDto.getLastName());
    student.setDob(studentCreateDto.getDob());
//    entityManager.persist(student);
//    entityManager.commit();
    Student saved = repository.save(student);
    log.debug("Student is saved {}", saved);
    return "Student is saved: " + saved;
  }

  @Override
  public String getStudent(Long id) {
    Optional<Student> optionalStudent = repository.findById(id);
    if (optionalStudent.isPresent()) {
      Student student = optionalStudent.get();
      return new StudentDto(student.getFirstName(), student.getLastName(), student.getDob()).toString();
    } else {
      return "No such student";
    }
  }

  @Override
  public StudentDto updateStudent(Long id) {
    return null;
  }

  @Override
  public String deleteStudent(Long id) {
    return null;
  }
}
