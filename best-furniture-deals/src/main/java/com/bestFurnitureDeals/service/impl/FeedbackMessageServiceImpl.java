package com.bestFurnitureDeals.service.impl;

import com.bestFurnitureDeals.dao.FeedbackMessageDAO;
import com.bestFurnitureDeals.exception.NoFeedbackMessageFoundException;
import com.bestFurnitureDeals.exception.OrderNotFinishedException;
import com.bestFurnitureDeals.model.ClientOrder;
import com.bestFurnitureDeals.model.FeedbackMessage;
import com.bestFurnitureDeals.model.OrderState;
import com.bestFurnitureDeals.service.ClientOrderService;
import com.bestFurnitureDeals.service.FeedbackMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FeedbackMessageServiceImpl implements FeedbackMessageService {
    private ClientOrderService clientOrderService;
    private FeedbackMessageDAO feedbackMessageDAO;

    @Autowired
    public FeedbackMessageServiceImpl(ClientOrderService clientOrderService, FeedbackMessageDAO feedbackMessageDAO) {
        this.clientOrderService = clientOrderService;
        this.feedbackMessageDAO = feedbackMessageDAO;
    }

    @Override
    public void postFeedbackMessageToAnOrder(long orderId, FeedbackMessage feedbackMessage) {
        ClientOrder clientOrder = clientOrderService.getClientOrderById(orderId);
        if (clientOrder.getOrderHistory().getOrderState() != OrderState.COMPLETED) {
            throw new OrderNotFinishedException();
        }
        feedbackMessage.setOrderHistory(clientOrder.getOrderHistory());
        feedbackMessageDAO.save(feedbackMessage);
    }

    @Override
    public FeedbackMessage getFeedbackMessageById(long id) {
        return feedbackMessageDAO.findById(id).orElseThrow(() -> new NoFeedbackMessageFoundException("No feedback message with id " + id + " found!"));
    }

    @Override
    @PreAuthorize("hasAuthority('STAFF')")
    public FeedbackMessage getFeedbackMessageByOrderId(long id) {
        return clientOrderService.getClientOrderById(id).getOrderHistory().getFeedbackMessage();
    }
}
