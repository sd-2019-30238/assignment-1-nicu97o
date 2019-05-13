package com.bestfurnituredeals.assignment3.handler.impl;

import com.bestfurnituredeals.assignment3.handler.RequestHandler;
import com.bestfurnituredeals.assignment3.model.database.FeedbackMessage;
import com.bestfurnituredeals.assignment3.model.write.FeedbackMessagePostCommandDTO;
import com.bestfurnituredeals.assignment3.request.Request;
import com.bestfurnituredeals.assignment3.service.command.FeedbackMessageCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeedbackRequestHandler implements RequestHandler {
    private FeedbackMessageCommandService feedbackMessageCommandService;

    @Autowired
    public FeedbackRequestHandler(FeedbackMessageCommandService feedbackMessageCommandService) {
        this.feedbackMessageCommandService = feedbackMessageCommandService;
    }

    @Override
    public void handleRequest(Request request) {
        if (request.getRequestName().equals("addFeedbackMessage")) {
            FeedbackMessage feedbackMessage = new FeedbackMessage();
            feedbackMessage.setTitle(((FeedbackMessagePostCommandDTO) request.getRequestObject()).getTitle());
            feedbackMessage.setMessageBody(((FeedbackMessagePostCommandDTO) request.getRequestObject()).getMessageBody());
            long orderId = ((FeedbackMessagePostCommandDTO) request.getRequestObject()).getOrderId();
            feedbackMessageCommandService.postFeedbackMessageToAnOrder(orderId, feedbackMessage);
        }
    }
}
