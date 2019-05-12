package com.bestfurnituredeals.assignment3.exception;

public class NoFurnitureFoundException extends RuntimeException {
    public NoFurnitureFoundException() {
        super("No furniture found!");
    }

    public NoFurnitureFoundException(String msg) {
        super(msg);
    }
}
