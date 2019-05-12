package com.bestfurnituredeals.assignment3.exception;

public class OrderAlreadyPlacedException extends RuntimeException {
    public OrderAlreadyPlacedException() {
        super("Order already placed!");
    }
}
