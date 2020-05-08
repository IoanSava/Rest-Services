package com.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DuplicateEntityExceptionAdvice {
    @ExceptionHandler(value = DuplicateEntityException.class)
    public ResponseEntity<Error> handleDuplicateEntityException(DuplicateEntityException exception) {
        Error error = new Error(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
