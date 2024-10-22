package com.school.driver.domain.service.impl;

import com.school.driver.domain.exception.BaseSchoolDriverException;
import com.school.driver.domain.model.Student;
import com.school.driver.domain.model.constants.MessageConstants;
import com.school.driver.domain.model.enums.StudentStatus;
import com.school.driver.domain.repository.StudentRepository;
import com.school.driver.domain.service.MessageBundleService;
import com.school.driver.domain.service.PostalCodeService;
import com.school.driver.domain.service.StudentDomainService;
import com.school.driver.domain.vo.request.StudentListFilterVO;
import com.school.driver.domain.vo.response.StudentPaginatedVO;
import com.school.driver.domain.vo.response.ValidationResponseVO;
import org.springframework.http.HttpStatus;


public class StudentDomainServiceImpl implements StudentDomainService {

    private final StudentRepository repository;
    private final MessageBundleService messageBundleService;
    private final PostalCodeService postalCodeService;

    public StudentDomainServiceImpl(StudentRepository repository, MessageBundleService messageBundleService, PostalCodeService postalCodeService) {
        this.repository = repository;
        this.messageBundleService = messageBundleService;
        this.postalCodeService = postalCodeService;
    }

    @Override
    public Student createStudent(Student student) {
        student.setStatus(StudentStatus.ENABLED);
        treatValidations(student.canCreateStudent());
        try {
            postalCodeService.findPostalCode(student.getAddress().getPostalCode());
        }catch (Exception e){
            throw new BaseSchoolDriverException(messageBundleService.getMessage(MessageConstants.MESSAGE_POSTAL_CODE_API_FIND_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return repository.saveStudent(student);
    }

    @Override
    public Student updateStudent(Student student) {
        treatValidations(student.canUpdateStudent());
        try {
            postalCodeService.findPostalCode(student.getAddress().getPostalCode());
        }catch (Exception e){
            throw new BaseSchoolDriverException(messageBundleService.getMessage(MessageConstants.MESSAGE_POSTAL_CODE_API_FIND_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }        return repository.saveStudent(student);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = findById(id);
        treatValidations(student.canDeleteStudent());
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

    @Override
    public void treatValidations(ValidationResponseVO validationResponseVO) {
        if(!validationResponseVO.getSuccess()){
            throw new BaseSchoolDriverException(messageBundleService.getMessageList(validationResponseVO.getValidationErrorMessages()), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
