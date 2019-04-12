package com.bestFurnitureDeals.exception;

public class OrderNotApprovedException extends RuntimeException {
    public OrderNotApprovedException() {
        super("Order is not approved!");
    }
}
