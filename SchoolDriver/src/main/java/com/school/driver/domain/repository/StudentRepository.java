package com.school.driver.domain.repository;

import com.school.driver.domain.model.Student;
import com.school.driver.domain.vo.request.StudentListFilterVO;
import com.school.driver.domain.vo.response.StudentPaginatedVO;

import java.util.Optional;

public interface StudentRepository {

    Student saveStudent(Student student);

    Optional<Student> findById(Long id);

    StudentPaginatedVO listStudents(StudentListFilterVO filterVO);

    void deleteStudent(Long id);
}
