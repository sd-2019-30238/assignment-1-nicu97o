package com.bestfurnituredeals.assignment3.model.read.mapper.impl;

import com.bestfurnituredeals.assignment3.model.database.FeedbackMessage;
import com.bestfurnituredeals.assignment3.model.database.OrderHistory;
import com.bestfurnituredeals.assignment3.model.read.mapper.Mapper;
import com.bestfurnituredeals.assignment3.model.read.dto.FeedbackMessageDTO;
import com.bestfurnituredeals.assignment3.model.read.dto.OrderHistoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeedbackMessageMapper implements Mapper<FeedbackMessage, FeedbackMessageDTO> {
    private Mapper<OrderHistory, OrderHistoryDTO> orderHistoryMapper;

    @Autowired
    public FeedbackMessageMapper(Mapper<OrderHistory, OrderHistoryDTO> orderHistoryMapper) {
        this.orderHistoryMapper = orderHistoryMapper;
    }

    @Override
    public FeedbackMessage convertToEntity(FeedbackMessageDTO feedbackMessageDTO) {
        FeedbackMessage feedbackMessage = new FeedbackMessage();
        feedbackMessage.setId(feedbackMessageDTO.getId());
        feedbackMessage.setTitle(feedbackMessageDTO.getTitle());
        feedbackMessage.setMessageBody(feedbackMessageDTO.getMessageBody());
        if (feedbackMessageDTO.getOrderHistory() != null) {
            feedbackMessage.setOrderHistory(orderHistoryMapper.convertToEntity(feedbackMessageDTO.getOrderHistory()));
        }
        return feedbackMessage;
    }

    @Override
    public FeedbackMessageDTO convertToDTO(FeedbackMessage feedbackMessage) {
        FeedbackMessageDTO feedbackMessageDTO = new FeedbackMessageDTO();
        feedbackMessageDTO.setId(feedbackMessage.getId());
        feedbackMessageDTO.setTitle(feedbackMessage.getTitle());
        feedbackMessageDTO.setMessageBody(feedbackMessage.getMessageBody());
        if (feedbackMessage.getOrderHistory() != null) {
            feedbackMessageDTO.setOrderHistory(orderHistoryMapper.convertToDTO(feedbackMessage.getOrderHistory()));
        }
        return feedbackMessageDTO;
    }
}
