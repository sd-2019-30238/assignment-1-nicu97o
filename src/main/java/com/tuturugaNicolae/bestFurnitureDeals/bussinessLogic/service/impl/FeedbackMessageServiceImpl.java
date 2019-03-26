package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.IsStaff;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.FeedbackMessageService;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.FeedbackMessageDAO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.FeedbackMessage;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.OrderHistory;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.OrderState;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.exception.CantPostFeedbackMessageException;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.exception.NoFeedBackMessageFound;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class FeedbackMessageServiceImpl implements FeedbackMessageService {
    private FeedbackMessageDAO feedbackMessageDAO;

    @Autowired
    public FeedbackMessageServiceImpl(FeedbackMessageDAO feedbackMessageDAO) {
        this.feedbackMessageDAO = feedbackMessageDAO;
    }

    @Override
    public void addFeedbackMessageToAnOrder(FeedbackMessage feedbackMessage, OrderHistory orderHistory) {
        Optional<FeedbackMessage> oldFeedbackMessage = feedbackMessageDAO.getFeedbackMessageByClientOrderId(orderHistory.getClientOrder().getId());
        if (oldFeedbackMessage.isPresent()) {
            throw new CantPostFeedbackMessageException("Feedback message already posted!");
        }
        if (orderHistory.getOrderState() != OrderState.COMPLETED) {
            throw new CantPostFeedbackMessageException("Order not yet completed!");
        }
        feedbackMessage.setOrderHistory(orderHistory);
        feedbackMessageDAO.insert(feedbackMessage);
    }

    @Override
    @IsStaff
    public FeedbackMessage getFeedbackMessageByClientOrderId(long id) {
        Optional<FeedbackMessage> feedbackMessage = feedbackMessageDAO.getFeedbackMessageByClientOrderId(id);
        if (!feedbackMessage.isPresent()) {
            throw new NoFeedBackMessageFound();
        }
        return feedbackMessage.get();
    }
}
