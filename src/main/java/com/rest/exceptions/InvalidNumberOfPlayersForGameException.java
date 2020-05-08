package com.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidNumberOfPlayersForGameException extends RuntimeException {
    public InvalidNumberOfPlayersForGameException(String message) {
        super(message);
    }
}
