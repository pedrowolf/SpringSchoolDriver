package com.school.driver.domain.exception;

import com.school.driver.application.dto.response.ErrorResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BaseSchoolDriverException extends RuntimeException{

    private List<ErrorResponseDTO> errorResponseDTOS;
    private HttpStatus httpStatus;

    public BaseSchoolDriverException(String message, HttpStatus httpStatus){
        this.errorResponseDTOS = new ArrayList<>(Collections.singletonList(new ErrorResponseDTO(message, httpStatus.value(), httpStatus.getReasonPhrase())));
        this.httpStatus = httpStatus;
    }

    public BaseSchoolDriverException(List<String> message, HttpStatus httpStatus){
        this.errorResponseDTOS = message.stream().map(m -> new ErrorResponseDTO(m, httpStatus.value(), httpStatus.getReasonPhrase())).collect(Collectors.toList());
        this.httpStatus = httpStatus;
    }
}
