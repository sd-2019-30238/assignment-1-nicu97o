package com.bestFurnitureDeals.exception;

public class NoClientOrderFoundException extends RuntimeException {
    public NoClientOrderFoundException() {
        super("No order found!");
    }

    public NoClientOrderFoundException(String msg) {
        super(msg);
    }
}
