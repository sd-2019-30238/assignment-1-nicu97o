package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.exception;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException() {
        super("Access forbidden!");
    }
}
