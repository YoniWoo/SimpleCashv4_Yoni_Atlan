package com.simplecash.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .filter(fieldError -> fieldError.getDefaultMessage() != null &&
                        !fieldError.getDefaultMessage().isEmpty())
                .collect(
                        toMap(
                                FieldError::getField,
                                FieldError::getDefaultMessage,
                                (m1, m2) -> m1
                        )
                );
    }
}
