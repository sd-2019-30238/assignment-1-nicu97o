package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.exception;

public class NoBoughtFurnitureFoundException extends RuntimeException {
    public NoBoughtFurnitureFoundException() {
        super("No bought furniture found!");
    }

    public NoBoughtFurnitureFoundException(String msg) {
        super(msg);
    }
}
