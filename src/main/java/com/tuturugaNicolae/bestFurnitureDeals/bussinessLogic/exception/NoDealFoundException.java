package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.exception;

public class NoDealFoundException extends RuntimeException {
    public NoDealFoundException() {
        super("No deal found!");
    }

    public NoDealFoundException(String msg) {
        super(msg);
    }
}
