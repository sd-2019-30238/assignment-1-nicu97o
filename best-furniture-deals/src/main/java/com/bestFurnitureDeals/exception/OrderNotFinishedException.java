package com.bestFurnitureDeals.exception;

public class OrderNotFinishedException extends RuntimeException {
    public OrderNotFinishedException() {
        super("Order not finished!");
    }
}
