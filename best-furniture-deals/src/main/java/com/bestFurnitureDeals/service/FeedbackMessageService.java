package com.bestFurnitureDeals.service;

import com.bestFurnitureDeals.model.FeedbackMessage;

public interface FeedbackMessageService {
    void postFeedbackMessageToAnOrder(long orderId, FeedbackMessage feedbackMessage);
}
