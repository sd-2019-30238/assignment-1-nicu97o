package com.tuturugaNicolae.bestFurnitureDeals.exception;

public class InvalidClientOrderException extends RuntimeException {
    public InvalidClientOrderException() {
        super("Invalid client order!");
    }
}
