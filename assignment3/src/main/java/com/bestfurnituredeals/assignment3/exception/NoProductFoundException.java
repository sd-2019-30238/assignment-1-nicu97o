package com.bestfurnituredeals.assignment3.exception;

public class NoProductFoundException extends RuntimeException {
    public NoProductFoundException() {
        super("No product found!");
    }

    public NoProductFoundException(String msg) {
        super(msg);
    }
}
