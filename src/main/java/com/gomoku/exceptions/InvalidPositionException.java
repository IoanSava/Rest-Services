package com.gomoku.exceptions;

public class InvalidPositionException extends Exception {
    public InvalidPositionException() {
        super("Invalid position of move");
    }
}
