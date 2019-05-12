package com.bestfurnituredeals.assignment3.exception;

public class DealUnavailableException extends RuntimeException {
    public DealUnavailableException() {
        super("Deal unavailable!");
    }
}
