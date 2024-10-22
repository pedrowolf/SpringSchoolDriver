package com.school.driver.application.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.driver.application.dto.request.StudentCreateRequestDTO;
import com.school.driver.application.dto.request.StudentListFilterRequestDTO;
import com.school.driver.application.dto.request.StudentUpdateRequestDTO;
import com.school.driver.application.dto.response.IdResponseDTO;
import com.school.driver.application.dto.response.ResponsePageableDTO;
import com.school.driver.application.dto.response.StudentResponseDTO;
import com.school.driver.domain.exception.BaseSchoolDriverException;
import com.school.driver.domain.mapper.PostalCodeMapper;
import com.school.driver.domain.model.Student;
import com.school.driver.domain.model.constants.MessageConstants;
import com.school.driver.domain.service.MessageBundleService;
import com.school.driver.domain.service.PostalCodeService;
import com.school.driver.domain.service.StudentDomainService;
import com.school.driver.domain.vo.request.StudentListFilterVO;
import com.school.driver.domain.vo.response.PostalCodeResponseVO;
import com.school.driver.domain.vo.response.StudentPaginatedVO;
import com.school.driver.infrastructure.dto.response.PostalCodeResponseDTO;
import com.school.driver.infrastructure.util.PageableResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/postal-code")
@RequiredArgsConstructor
@Tag(name = "Postal Code Controller", description = "Controller to find postal codes")
public class PostalCodeController {

    private final PostalCodeService postalCodeService;
    private final PostalCodeMapper postalCodeMapper;

    @GetMapping("/{postalCode}")
    @Operation(summary = "Consultar CEP")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CEP consultado com sucesso")
    })
    public PostalCodeResponseDTO findPostalCode(@PathVariable String postalCode){
        PostalCodeResponseVO responseVO = postalCodeService.findPostalCode(postalCode);
        return postalCodeMapper.fromVoToDto(responseVO);
    }
}
