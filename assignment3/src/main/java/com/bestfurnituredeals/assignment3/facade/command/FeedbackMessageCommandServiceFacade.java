package com.bestfurnituredeals.assignment3.facade.command;

import com.bestfurnituredeals.assignment3.model.database.FeedbackMessage;
import com.bestfurnituredeals.assignment3.model.write.FeedbackMessagePostCommandDTO;

public interface FeedbackMessageCommandServiceFacade {
    void postFeedbackMessageToAnOrder(long orderId, FeedbackMessagePostCommandDTO feedbackMessagePostCommandDTO);
}
