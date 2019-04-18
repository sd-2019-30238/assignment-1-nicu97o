package com.tuturugaNicolae.bestFurnitureDeals.exception;

public class NotEnoughProductsOnStockException extends RuntimeException {
    public NotEnoughProductsOnStockException() {
        super("Not enough products on stock!");
    }
}
