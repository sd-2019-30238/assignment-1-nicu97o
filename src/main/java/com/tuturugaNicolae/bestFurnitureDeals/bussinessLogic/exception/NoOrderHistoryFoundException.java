package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.exception;

public class NoOrderHistoryFoundException extends RuntimeException {
    public NoOrderHistoryFoundException() {
        super("No order history found!");
    }
}
