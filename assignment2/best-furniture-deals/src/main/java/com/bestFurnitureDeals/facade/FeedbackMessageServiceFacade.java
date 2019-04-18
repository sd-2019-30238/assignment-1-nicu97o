package com.bestFurnitureDeals.facade;

import com.bestFurnitureDeals.dto.model.FeedbackMessageDTO;

public interface FeedbackMessageServiceFacade {
    void postFeedbackMessageToAnOrder(long orderId, FeedbackMessageDTO feedbackMessageDTO);

    FeedbackMessageDTO getFeedbackMessageById(long id);

    FeedbackMessageDTO getFeedbackMessageByOrderId(long id);
}
