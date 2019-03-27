package com.tuturugaNicolae.bestFurnitureDeals.exception;

public class OrderNotPlacedException extends RuntimeException {
    public OrderNotPlacedException() {
        super("Order not placed!");
    }
}
