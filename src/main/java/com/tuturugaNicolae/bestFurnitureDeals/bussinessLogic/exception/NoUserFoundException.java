package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.exception;

public class NoUserFoundException extends RuntimeException {
    public NoUserFoundException() {
        super("No user found!");
    }

    public NoUserFoundException(String msg) {
        super(msg);
    }
}
