package com.school.driver.application.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.driver.application.dto.request.StudentListFilterRequestDTO;
import com.school.driver.application.dto.request.StudentCreateRequestDTO;
import com.school.driver.application.dto.request.StudentUpdateRequestDTO;
import com.school.driver.application.dto.response.IdResponseDTO;
import com.school.driver.application.dto.response.ResponsePageableDTO;
import com.school.driver.application.dto.response.StudentResponseDTO;
import com.school.driver.domain.model.Student;
import com.school.driver.domain.service.StudentDomainService;
import com.school.driver.domain.vo.request.StudentListFilterVO;
import com.school.driver.domain.vo.response.StudentPaginatedVO;
import com.school.driver.infrastructure.util.PageableResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/students")
@RequiredArgsConstructor
@Tag(name = "Student Controller", description = "Controller to manage students")
public class StudentController {

    private final StudentDomainService studentDomainService;
    private final ObjectMapper objectMapper;

    @PostMapping
    @Operation(summary = "Cadastrar aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno cadastrado com sucesso")
    })
    public IdResponseDTO createStudent(@RequestBody @Valid StudentCreateRequestDTO requestDTO){
        Student student = studentDomainService.createStudent(objectMapper.convertValue(requestDTO, Student.class));
        return new IdResponseDTO(student.getId());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editar aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno editado com sucesso")
    })
    public void updateStudent(@RequestBody @Valid StudentUpdateRequestDTO requestDTO,
                              @PathVariable Long id){
        Student student = objectMapper.convertValue(requestDTO, Student.class);
        student.setId(id);
        studentDomainService.updateStudent(student);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno removido com sucesso")
    })
    public void deleteStudent(@PathVariable Long id){
        studentDomainService.deleteStudent(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno consultado com sucesso")
    })
    public StudentResponseDTO findStudent(@PathVariable Long id){
        return objectMapper.convertValue(studentDomainService.findById(id), StudentResponseDTO.class);
    }

    @GetMapping
    @Operation(summary = "Listar alunos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alunos listados com sucesso")
    })
    public ResponsePageableDTO<StudentResponseDTO> findStudent(StudentListFilterRequestDTO requestDTO){
        StudentPaginatedVO paginatedVO = studentDomainService.listStudents(objectMapper.convertValue(requestDTO, StudentListFilterVO.class));
        return new ResponsePageableDTO<>(paginatedVO.getStudents().stream().map(i -> objectMapper.convertValue(i, StudentResponseDTO.class)).toList(),
                PageableResponseUtil.builPageResponseDTO(requestDTO, paginatedVO.getTotalItems()));
    }
}
