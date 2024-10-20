package com.school.driver.infrastructure.exception;

import com.school.driver.application.dto.response.ErrorResponseDTO;
import com.school.driver.domain.exception.BaseSchoolDriverException;
import com.school.driver.domain.model.constants.MessageConstants;
import com.school.driver.domain.service.MessageBundleService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class WebRestControllerAdvice {

    private final MessageBundleService messageBundleService;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponseDTO>> handleMethodArgumentNotValid(MethodArgumentNotValidException exception){
        List<ErrorResponseDTO> errors = new ArrayList<>();
        BindingResult result = exception.getBindingResult();
        result.getFieldErrors().forEach(e -> {
            errors.add(new ErrorResponseDTO(messageBundleService.getMessage(e.getDefaultMessage()), HttpStatus.UNPROCESSABLE_ENTITY.value(), HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase()));
        });
        return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<ErrorResponseDTO>> handleMethodArgumentNotValid(BindException exception){
        List<ErrorResponseDTO> errors = new ArrayList<>();
        BindingResult result = exception.getBindingResult();
        result.getFieldErrors().forEach(e -> {
            errors.add(new ErrorResponseDTO(messageBundleService.getMessage(e.getDefaultMessage()), HttpStatus.UNPROCESSABLE_ENTITY.value(), HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase()));
        });
        return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(BaseSchoolDriverException.class)
    public ResponseEntity<List<ErrorResponseDTO>> handleMethodArgumentNotValid(BaseSchoolDriverException exception){
        return new ResponseEntity<>(Collections.singletonList(new ErrorResponseDTO(exception.getMessage(), exception.getHttpStatus().value(), exception.getHttpStatus().getReasonPhrase())), exception.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<List<ErrorResponseDTO>> handleMethodArgumentNotValid(Exception exception){
        return new ResponseEntity<>(Collections.singletonList(new ErrorResponseDTO(messageBundleService.getMessage(MessageConstants.MESSAGE_INTERNAL_ERROR), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())),  HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
