package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.exception;

public class InvalidDealException extends RuntimeException {
    public InvalidDealException() {
        super("Invalid deal!");
    }

    public InvalidDealException(String msg) {
        super(msg);
    }
}
