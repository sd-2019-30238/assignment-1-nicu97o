package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.exception;

public class NoClientOrderFoundException extends RuntimeException {
    public NoClientOrderFoundException() {
        super("No client order found!");
    }

    public NoClientOrderFoundException(String msg) {
        super(msg);
    }
}
