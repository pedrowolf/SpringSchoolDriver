package com.school.driver.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class BaseSchoolDriverException extends RuntimeException{

    private String message;

    private HttpStatus httpStatus;
}
