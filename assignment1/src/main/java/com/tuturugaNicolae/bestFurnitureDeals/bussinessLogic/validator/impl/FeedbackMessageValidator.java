package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.FeedbackMessageDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.Validator;
import org.springframework.stereotype.Component;

@Component
public class FeedbackMessageValidator implements Validator<FeedbackMessageDTO> {
    @Override
    public boolean validate(FeedbackMessageDTO feedbackMessageDTO) {
        return feedbackMessageDTO != null && validateTitle(feedbackMessageDTO.getTitle()) && validateMessage(feedbackMessageDTO.getMessageBody());
    }

    private boolean validateTitle(String title) {
        return title != null && title.length() >= 3;
    }

    private boolean validateMessage(String message) {
        return message != null && message.length() >= 5;
    }
}
