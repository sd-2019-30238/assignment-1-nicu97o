package com.bestfurnituredeals.assignment3.exception;

public class NoUserFoundException extends RuntimeException {
    public NoUserFoundException() {
        super("No user found!");
    }

    public NoUserFoundException(String msg) {
        super(msg);
    }
}
