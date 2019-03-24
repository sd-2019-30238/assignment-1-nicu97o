package com.tuturugaNicolae.bestFurnitureDeals.exception;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException() {
        super("Access forbidden!");
    }
}
