package com.tuturugaNicolae.bestFurnitureDeals.exception;

public class InvalidDealException extends RuntimeException {
    public InvalidDealException() {
        super("Invalid deal!");
    }

    public InvalidDealException(String msg) {
        super(msg);
    }
}
