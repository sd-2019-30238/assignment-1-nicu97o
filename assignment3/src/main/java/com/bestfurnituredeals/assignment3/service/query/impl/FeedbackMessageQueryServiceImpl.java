package com.bestfurnituredeals.assignment3.service.query.impl;

import com.bestfurnituredeals.assignment3.dao.FeedbackMessageDAO;
import com.bestfurnituredeals.assignment3.exception.NoFeedbackMessageFoundException;
import com.bestfurnituredeals.assignment3.model.database.FeedbackMessage;
import com.bestfurnituredeals.assignment3.service.query.ClientOrderQueryService;
import com.bestfurnituredeals.assignment3.service.query.FeedbackMessageQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FeedbackMessageQueryServiceImpl implements FeedbackMessageQueryService {
    private FeedbackMessageDAO feedbackMessageDAO;
    private ClientOrderQueryService clientOrderQueryService;

    @Autowired
    public FeedbackMessageQueryServiceImpl(FeedbackMessageDAO feedbackMessageDAO, ClientOrderQueryService clientOrderQueryService) {
        this.feedbackMessageDAO = feedbackMessageDAO;
        this.clientOrderQueryService = clientOrderQueryService;
    }

    @Override
    public FeedbackMessage getFeedbackMessageById(long id) {
        return feedbackMessageDAO.findById(id).orElseThrow(() -> new NoFeedbackMessageFoundException("No feedback message with id " + id + " found!"));
    }

    @Override
    @PreAuthorize("hasAuthority('STAFF')")
    public FeedbackMessage getFeedbackMessageByOrderId(long id) {
        FeedbackMessage feedbackMessage = clientOrderQueryService.getClientOrderById(id).getOrderHistory().getFeedbackMessage();
        if (feedbackMessage == null) {
            throw new NoFeedbackMessageFoundException("Feedback message not posted yet!");
        }
        return feedbackMessage;
    }
}
