package com.bestfurnituredeals.assignment3.facade.query.impl;

import com.bestfurnituredeals.assignment3.facade.query.FeedbackMessageQueryServiceFacade;
import com.bestfurnituredeals.assignment3.model.database.FeedbackMessage;
import com.bestfurnituredeals.assignment3.model.read.dto.FeedbackMessageDTO;
import com.bestfurnituredeals.assignment3.model.read.mapper.Mapper;
import com.bestfurnituredeals.assignment3.service.query.FeedbackMessageQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeedbackMessageQueryServiceFacadeImpl implements FeedbackMessageQueryServiceFacade {
    private FeedbackMessageQueryService feedbackMessageQueryService;
    private Mapper<FeedbackMessage, FeedbackMessageDTO> mapper;

    @Autowired
    public FeedbackMessageQueryServiceFacadeImpl(FeedbackMessageQueryService feedbackMessageQueryService, Mapper<FeedbackMessage, FeedbackMessageDTO> mapper) {
        this.feedbackMessageQueryService = feedbackMessageQueryService;
        this.mapper = mapper;
    }

    @Override
    public FeedbackMessageDTO getFeedbackMessageById(long id) {
        return mapper.convertToDTO(feedbackMessageQueryService.getFeedbackMessageById(id));
    }

    @Override
    public FeedbackMessageDTO getFeedbackMessageByOrderId(long id) {
        return mapper.convertToDTO(feedbackMessageQueryService.getFeedbackMessageByOrderId(id));
    }
}
