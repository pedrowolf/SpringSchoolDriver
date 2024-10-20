package com.school.driver.infrastructure.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.driver.domain.model.Student;
import com.school.driver.domain.repository.StudentRepository;
import com.school.driver.domain.vo.request.StudentListFilterVO;
import com.school.driver.domain.vo.response.StudentPaginatedVO;
import com.school.driver.infrastructure.model.StudentEntity;
import com.school.driver.infrastructure.repository.jpa.JpaStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {

    private final JpaStudentRepository jpaStudentRepository;

    private final ObjectMapper objectMapper;

    @Override
    public Student saveStudent(Student student) {
        StudentEntity entity = objectMapper.convertValue(student, StudentEntity.class);
        StudentEntity savedEntity = jpaStudentRepository.save(entity);
        return objectMapper.convertValue(savedEntity, Student.class);
    }

    @Override
    public Optional<Student> findById(Long id) {
        Optional<StudentEntity> entity = jpaStudentRepository.findById(id);
        return entity.map(studentEntity -> objectMapper.convertValue(studentEntity, Student.class));
    }

    @Override
    public StudentPaginatedVO listStudents(StudentListFilterVO filterVO) {
        Pageable pageable = PageRequest.of(filterVO.getPageNumber() - 1, filterVO.getPageSize());
        Page<StudentEntity> page = jpaStudentRepository.findAllByFilters(filterVO.getName(),
                filterVO.getFatherName(),
                filterVO.getMotherName(),
                pageable);
        return new StudentPaginatedVO(page.getContent().stream().map(i -> objectMapper.convertValue(i, Student.class)).toList(), (int)page.getTotalElements());
    }

    @Override
    public void deleteStudent(Long id) {
        jpaStudentRepository.deleteById(id);
    }
}
