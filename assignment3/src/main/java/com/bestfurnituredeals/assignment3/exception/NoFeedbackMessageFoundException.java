package com.bestfurnituredeals.assignment3.exception;

public class NoFeedbackMessageFoundException extends RuntimeException {
    public NoFeedbackMessageFoundException() {
        super("No feedback message found!");
    }

    public NoFeedbackMessageFoundException(String msg) {
        super(msg);
    }
}
