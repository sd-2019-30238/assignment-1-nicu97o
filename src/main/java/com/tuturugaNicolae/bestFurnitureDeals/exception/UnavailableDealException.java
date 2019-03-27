package com.tuturugaNicolae.bestFurnitureDeals.exception;

public class UnavailableDealException extends RuntimeException {
    public UnavailableDealException() {
        super("Deal unavailable!");
    }

    public UnavailableDealException(String msg) {
        super(msg);
    }
}
