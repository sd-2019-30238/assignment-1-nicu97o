package com.tuturugaNicolae.bestFurnitureDeals.exception;

public class NoRightsToPerformThisOperationException extends RuntimeException {
    public NoRightsToPerformThisOperationException() {
        super("You have no rights to perform this operation!");
    }

    public NoRightsToPerformThisOperationException(String msg) {
        super(msg);
    }
}
