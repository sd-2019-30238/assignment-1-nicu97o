package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.exception;

public class InvalidClientOrderException extends RuntimeException {
    public InvalidClientOrderException() {
        super("Invalid client order!");
    }
}
