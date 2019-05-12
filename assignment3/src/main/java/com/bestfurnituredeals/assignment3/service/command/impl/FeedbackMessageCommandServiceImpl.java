package com.bestfurnituredeals.assignment3.service.command.impl;

import com.bestfurnituredeals.assignment3.dao.FeedbackMessageDAO;
import com.bestfurnituredeals.assignment3.exception.OrderNotFinishedException;
import com.bestfurnituredeals.assignment3.model.database.ClientOrder;
import com.bestfurnituredeals.assignment3.model.database.FeedbackMessage;
import com.bestfurnituredeals.assignment3.model.database.OrderState;
import com.bestfurnituredeals.assignment3.service.command.FeedbackMessageCommandService;
import com.bestfurnituredeals.assignment3.service.query.ClientOrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FeedbackMessageCommandServiceImpl implements FeedbackMessageCommandService {
    private ClientOrderQueryService clientOrderQueryService;
    private FeedbackMessageDAO feedbackMessageDAO;

    @Autowired
    public FeedbackMessageCommandServiceImpl(ClientOrderQueryService clientOrderQueryService, FeedbackMessageDAO feedbackMessageDAO) {
        this.clientOrderQueryService = clientOrderQueryService;
        this.feedbackMessageDAO = feedbackMessageDAO;
    }

    @Override
    public void postFeedbackMessageToAnOrder(long orderId, FeedbackMessage feedbackMessage) {
        ClientOrder clientOrder = clientOrderQueryService.getClientOrderById(orderId);
        if (clientOrder.getOrderHistory().getOrderState() != OrderState.COMPLETED) {
            throw new OrderNotFinishedException();
        }
        feedbackMessage.setOrderHistory(clientOrder.getOrderHistory());
        feedbackMessageDAO.save(feedbackMessage);
    }
}
