package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.exception;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException() {
        super("Invalid user data!");
    }

    public InvalidUserException(String msg) {
        super(msg);
    }
}
