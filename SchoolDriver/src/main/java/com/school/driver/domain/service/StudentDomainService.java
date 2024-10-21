package com.school.driver.domain.service;

import com.school.driver.domain.model.Student;
import com.school.driver.domain.vo.request.StudentListFilterVO;
import com.school.driver.domain.vo.response.StudentPaginatedVO;
import com.school.driver.domain.vo.response.ValidationResponseVO;

public interface StudentDomainService {

    Student createStudent(Student student);

    Student updateStudent(Student student);

    void deleteStudent(Long id);

    Student findById(Long id);

    StudentPaginatedVO listStudents(StudentListFilterVO listFilterVO);

    void treatValidations(ValidationResponseVO validationResponseVO);
}
