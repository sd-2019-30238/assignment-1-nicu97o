package com.bestFurnitureDeals.exception;

public class NotEnoughProductsOnStockException extends RuntimeException {
    public NotEnoughProductsOnStockException() {
        super("There are not enough products on stock!");
    }
}
