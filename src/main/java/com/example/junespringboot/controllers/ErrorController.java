package com.example.junespringboot.controllers;

import com.example.junespringboot.models.dto.ErrorDTO;
import jakarta.validation.ConstraintViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorDTO errorValidation(ConstraintViolationException e) {
        return new ErrorDTO(500, e.getMessage());
    }
}
