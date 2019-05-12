package com.bestfurnituredeals.assignment3.facade.query;

import com.bestfurnituredeals.assignment3.model.read.dto.FeedbackMessageDTO;

public interface FeedbackMessageQueryServiceFacade {
    FeedbackMessageDTO getFeedbackMessageById(long id);

    FeedbackMessageDTO getFeedbackMessageByOrderId(long id);
}
