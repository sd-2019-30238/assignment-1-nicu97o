package com.bestfurnituredeals.assignment3.exception;

public class OrderNotApprovedException extends RuntimeException {
    public OrderNotApprovedException() {
        super("Order is not approved!");
    }
}
