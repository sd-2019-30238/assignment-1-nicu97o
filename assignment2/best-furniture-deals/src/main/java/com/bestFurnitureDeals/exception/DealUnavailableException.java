package com.bestFurnitureDeals.exception;

public class DealUnavailableException extends RuntimeException {
    public DealUnavailableException() {
        super("Deal unavailable!");
    }
}
