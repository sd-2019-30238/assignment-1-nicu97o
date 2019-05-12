package com.bestfurnituredeals.assignment3.facade.command.impl;

import com.bestfurnituredeals.assignment3.facade.command.FeedbackMessageCommandServiceFacade;
import com.bestfurnituredeals.assignment3.model.database.FeedbackMessage;
import com.bestfurnituredeals.assignment3.model.write.FeedbackMessagePostCommandDTO;
import com.bestfurnituredeals.assignment3.service.command.FeedbackMessageCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeedbackMessageCommandServiceFacadeImpl implements FeedbackMessageCommandServiceFacade {
    private FeedbackMessageCommandService feedbackMessageCommandService;

    @Autowired
    public FeedbackMessageCommandServiceFacadeImpl(FeedbackMessageCommandService feedbackMessageCommandService) {
        this.feedbackMessageCommandService = feedbackMessageCommandService;
    }

    @Override
    public void postFeedbackMessageToAnOrder(long orderId, FeedbackMessagePostCommandDTO feedbackMessagePostCommandDTO) {
        FeedbackMessage feedbackMessage = new FeedbackMessage();
        feedbackMessage.setTitle(feedbackMessagePostCommandDTO.getTitle());
        feedbackMessage.setMessageBody(feedbackMessagePostCommandDTO.getMessageBody());
        feedbackMessageCommandService.postFeedbackMessageToAnOrder(orderId, feedbackMessage);
    }
}
