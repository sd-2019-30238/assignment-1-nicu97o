package com.tuturugaNicolae.bestFurnitureDeals.exception;

public class NoOrderHistoryFoundException extends RuntimeException {
    public NoOrderHistoryFoundException() {
        super("No order history found!");
    }
}
