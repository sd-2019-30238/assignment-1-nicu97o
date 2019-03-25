package com.tuturugaNicolae.bestFurnitureDeals.exception;

public class NoDealFoundException extends RuntimeException {
    public NoDealFoundException() {
        super("No deal found!");
    }

    public NoDealFoundException(String msg) {
        super(msg);
    }
}
