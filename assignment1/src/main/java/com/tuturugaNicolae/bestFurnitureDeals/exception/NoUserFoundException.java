package com.tuturugaNicolae.bestFurnitureDeals.exception;

public class NoUserFoundException extends RuntimeException {
    public NoUserFoundException() {
        super("No user found!");
    }

    public NoUserFoundException(String msg) {
        super(msg);
    }
}
