package com.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class NotFoundExceptionAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Error> handleNotFoundException(NotFoundException exception) {
        Error error = new Error(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
