package com.tuturugaNicolae.bestFurnitureDeals.exception;

public class NoBoughtFurnitureFoundException extends RuntimeException {
    public NoBoughtFurnitureFoundException() {
        super("No bought furniture found!");
    }

    public NoBoughtFurnitureFoundException(String msg) {
        super(msg);
    }
}
