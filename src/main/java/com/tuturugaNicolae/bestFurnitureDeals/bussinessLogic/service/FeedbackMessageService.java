package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.FeedbackMessage;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.OrderHistory;

public interface FeedbackMessageService {
    void addFeedbackMessageToAnOrder(FeedbackMessage feedbackMessage, OrderHistory orderHistory);

    FeedbackMessage getFeedbackMessageByClientOrderId(long id);
}
