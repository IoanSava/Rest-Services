package com.gomoku.exceptions;

public class TooMuchPlayersException extends Exception {
    public TooMuchPlayersException() {
        super("Too much players");
    }
}
