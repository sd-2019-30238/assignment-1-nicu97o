package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.exception;

public class OrderNotPlacedException extends RuntimeException {
    public OrderNotPlacedException() {
        super("Order not placed!");
    }
}
