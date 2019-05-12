package com.bestfurnituredeals.assignment3.service.command;

import com.bestfurnituredeals.assignment3.model.database.FeedbackMessage;

public interface FeedbackMessageCommandService {
    void postFeedbackMessageToAnOrder(long orderId, FeedbackMessage feedbackMessage);
}
