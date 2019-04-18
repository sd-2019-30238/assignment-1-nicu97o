package com.tuturugaNicolae.bestFurnitureDeals.exception;

public class CantPostFeedbackMessageException extends RuntimeException {
    public CantPostFeedbackMessageException() {
        super("Feedback message already posted!");
    }

    public CantPostFeedbackMessageException(String msg) {
        super(msg);
    }
}
