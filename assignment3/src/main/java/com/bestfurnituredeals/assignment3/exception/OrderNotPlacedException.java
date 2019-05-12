package com.bestfurnituredeals.assignment3.exception;

public class OrderNotPlacedException extends RuntimeException {
    public OrderNotPlacedException() {
        super("Order not yet placed");
    }
}
