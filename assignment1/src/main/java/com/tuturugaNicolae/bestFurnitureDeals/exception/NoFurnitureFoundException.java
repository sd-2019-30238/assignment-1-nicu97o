package com.tuturugaNicolae.bestFurnitureDeals.exception;

public class NoFurnitureFoundException extends RuntimeException {
    public NoFurnitureFoundException() {
        super("No furniture found");
    }

    public NoFurnitureFoundException(String msg) {
        super(msg);
    }
}
