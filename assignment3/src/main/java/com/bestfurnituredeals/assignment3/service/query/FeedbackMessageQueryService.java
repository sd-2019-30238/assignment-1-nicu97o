package com.bestfurnituredeals.assignment3.service.query;

import com.bestfurnituredeals.assignment3.model.database.FeedbackMessage;

public interface FeedbackMessageQueryService {
    FeedbackMessage getFeedbackMessageById(long id);

    FeedbackMessage getFeedbackMessageByOrderId(long id);
}
