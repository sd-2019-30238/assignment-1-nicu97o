package com.tuturugaNicolae.bestFurnitureDeals.exception;

public class NoClientOrderFoundException extends RuntimeException {
    public NoClientOrderFoundException() {
        super("No client order found!");
    }

    public NoClientOrderFoundException(String msg) {
        super(msg);
    }
}
