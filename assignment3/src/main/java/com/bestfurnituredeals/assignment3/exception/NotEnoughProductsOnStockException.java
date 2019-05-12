package com.bestfurnituredeals.assignment3.exception;

public class NotEnoughProductsOnStockException extends RuntimeException {
    public NotEnoughProductsOnStockException() {
        super("There are not enough products on stock!");
    }
}
