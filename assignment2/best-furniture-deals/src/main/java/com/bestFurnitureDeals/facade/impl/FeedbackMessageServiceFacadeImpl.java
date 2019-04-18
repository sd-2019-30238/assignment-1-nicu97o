package com.bestFurnitureDeals.facade.impl;

import com.bestFurnitureDeals.dto.mapper.Mapper;
import com.bestFurnitureDeals.dto.model.FeedbackMessageDTO;
import com.bestFurnitureDeals.facade.FeedbackMessageServiceFacade;
import com.bestFurnitureDeals.model.FeedbackMessage;
import com.bestFurnitureDeals.service.FeedbackMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeedbackMessageServiceFacadeImpl implements FeedbackMessageServiceFacade {
    private FeedbackMessageService feedbackMessageService;
    private Mapper<FeedbackMessage, FeedbackMessageDTO> mapper;

    @Autowired
    public FeedbackMessageServiceFacadeImpl(FeedbackMessageService feedbackMessageService, Mapper<FeedbackMessage, FeedbackMessageDTO> mapper) {
        this.feedbackMessageService = feedbackMessageService;
        this.mapper = mapper;
    }

    @Override
    public void postFeedbackMessageToAnOrder(long orderId, FeedbackMessageDTO feedbackMessageDTO) {
        feedbackMessageService.postFeedbackMessageToAnOrder(orderId, mapper.convertToEntity(feedbackMessageDTO));
    }

    @Override
    public FeedbackMessageDTO getFeedbackMessageById(long id) {
        return mapper.convertToDTO(feedbackMessageService.getFeedbackMessageById(id));
    }

    @Override
    public FeedbackMessageDTO getFeedbackMessageByOrderId(long id) {
        return mapper.convertToDTO(feedbackMessageService.getFeedbackMessageByOrderId(id));
    }
}
