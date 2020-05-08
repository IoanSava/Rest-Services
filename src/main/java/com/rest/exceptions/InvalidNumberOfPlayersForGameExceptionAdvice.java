package com.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InvalidNumberOfPlayersForGameExceptionAdvice {
    @ExceptionHandler(value = InvalidNumberOfPlayersForGameException.class)
    public ResponseEntity<Error> handleInvalidNumberOfPlayersForGameException(InvalidNumberOfPlayersForGameException exception) {
        Error error = new Error(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
