package com.school.driver.domain.service.impl;

import com.school.driver.domain.exception.BaseSchoolDriverException;
import com.school.driver.domain.model.Student;
import com.school.driver.domain.model.constants.MessageConstants;
import com.school.driver.domain.model.enums.StudentStatus;
import com.school.driver.domain.repository.StudentRepository;
import com.school.driver.domain.service.MessageBundleService;
import com.school.driver.domain.service.StudentDomainService;
import com.school.driver.domain.vo.request.StudentListFilterVO;
import com.school.driver.domain.vo.response.StudentPaginatedVO;
import org.springframework.http.HttpStatus;

import java.util.Objects;

public class StudentDomainServiceImpl implements StudentDomainService {

    private final StudentRepository repository;
    private final MessageBundleService messageBundleService;

    public StudentDomainServiceImpl(StudentRepository repository, MessageBundleService messageBundleService) {
        this.repository = repository;
        this.messageBundleService = messageBundleService;
    }

    @Override
    public Student createStudent(Student student) {
        student.setStatus(StudentStatus.ENABLED);
        String validationErrorMessage = student.canCreateStudent();
        if(Objects.nonNull(validationErrorMessage)){
            throw new BaseSchoolDriverException(messageBundleService.getMessage(validationErrorMessage), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        //todo check external cep api
        return repository.saveStudent(student);
    }

    @Override
    public Student updateStudent(Student student) {
        String validationErrorMessage = student.canUpdateStudent();
        if(Objects.nonNull(validationErrorMessage)){
            throw new BaseSchoolDriverException(messageBundleService.getMessage(validationErrorMessage), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        //todo check external cep api
        return repository.saveStudent(student);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = findById(id);
        String validationErrorMessage = student.canDeleteStudent();
        if(Objects.nonNull(validationErrorMessage)){
            throw new BaseSchoolDriverException(messageBundleService.getMessage(validationErrorMessage), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        student.setStatus(StudentStatus.DELETED);
        repository.saveStudent(student);
    }

    @Override
    public Student findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new BaseSchoolDriverException(messageBundleService.getMessage(MessageConstants.MESSAGE_STUDENT_NOT_FOUND_ERROR), HttpStatus.NOT_FOUND));
    }

    @Override
    public StudentPaginatedVO listStudents(StudentListFilterVO listFilterVO) {
        return repository.listStudents(listFilterVO);
    }
}
