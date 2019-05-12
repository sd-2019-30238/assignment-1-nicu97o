package com.bestfurnituredeals.assignment3.exception;

public class OrderNotFinishedException extends RuntimeException {
    public OrderNotFinishedException() {
        super("Order not finished!");
    }
}
