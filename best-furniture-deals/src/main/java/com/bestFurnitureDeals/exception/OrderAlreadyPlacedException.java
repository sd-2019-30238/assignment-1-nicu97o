package com.bestFurnitureDeals.exception;

public class OrderAlreadyPlacedException extends RuntimeException {
    public OrderAlreadyPlacedException() {
        super("Order already placed!");
    }
}
