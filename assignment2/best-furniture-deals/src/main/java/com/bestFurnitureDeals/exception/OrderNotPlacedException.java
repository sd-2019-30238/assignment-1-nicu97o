package com.bestFurnitureDeals.exception;

public class OrderNotPlacedException extends RuntimeException {
    public OrderNotPlacedException() {
        super("Order not yet placed");
    }
}
