package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.FeedbackMessageDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.OrderHistoryDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.FeedbackMessage;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.OrderHistory;
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
        feedbackMessage.setOrderHistory(orderHistoryMapper.convertToEntity(feedbackMessageDTO.getOrderHistoryDTO()));
        return feedbackMessage;
    }

    @Override
    public FeedbackMessageDTO convertToDTO(FeedbackMessage feedbackMessage) {
        FeedbackMessageDTO feedbackMessageDTO = new FeedbackMessageDTO();
        feedbackMessageDTO.setId(feedbackMessage.getId());
        feedbackMessageDTO.setTitle(feedbackMessage.getTitle());
        feedbackMessageDTO.setMessageBody(feedbackMessage.getMessageBody());
        feedbackMessageDTO.setOrderHistoryDTO(orderHistoryMapper.convertToDTO(feedbackMessage.getOrderHistory()));
        return feedbackMessageDTO;
    }
}
