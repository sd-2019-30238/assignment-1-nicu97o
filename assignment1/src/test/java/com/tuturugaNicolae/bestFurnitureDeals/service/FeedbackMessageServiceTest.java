package com.tuturugaNicolae.bestFurnitureDeals.service;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.configuration.BusinessLogicConfiguration;
import com.tuturugaNicolae.bestFurnitureDeals.exception.CantPostFeedbackMessageException;
import com.tuturugaNicolae.bestFurnitureDeals.exception.NoFeedBackMessageFound;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.SecurityContext;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.FeedbackMessageDAO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.OrderHistoryDAO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.FeedbackMessage;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.OrderHistory;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.OrderState;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BusinessLogicConfiguration.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class FeedbackMessageServiceTest {
    @Autowired
    private FeedbackMessageService feedbackMessageService;
    @Autowired
    private SecurityContext securityContext;
    @Autowired
    private OrderHistoryDAO orderHistoryDAO;
    @Autowired
    private FeedbackMessageDAO feedbackMessageDAO;

    @Before
    public void setUp() {
        securityContext.authenticate(PREINSERTED_USER_2.getUsername(), PREINSERTED_USER_2.getPassword());
    }

    @Test(expected = CantPostFeedbackMessageException.class)
    public void testAddFeedbackMessageToAnOrderWhenAFeedbackMessageAlreadyExists_shouldThrowException() {
        feedbackMessageService.addFeedbackMessageToAnOrder(new FeedbackMessage(), PREINSERTED_ORDER_HISTORY_1);
    }

    @Test(expected = CantPostFeedbackMessageException.class)
    public void testAddFeedbackMessageToAnOrderWhenOrderIsNotCompleted_shouldThrowException() {
        feedbackMessageService.addFeedbackMessageToAnOrder(new FeedbackMessage(), PREINSERTED_ORDER_HISTORY_2);
    }

    @Test
    public void testAddFeedbackMessageToAnOrder_shouldBeSuccessful() {
        OrderHistory orderHistory = orderHistoryDAO.selectById(PREINSERTED_ORDER_HISTORY_2.getId());
        orderHistory.setOrderState(OrderState.COMPLETED);
        feedbackMessageService.addFeedbackMessageToAnOrder(new FeedbackMessage(), orderHistory);
        assertThat(feedbackMessageDAO.selectAll().size(), is(NUMBER_OF_PREINSERTED_FEEDBACK_MESSAGES + 1));
    }

    @Test(expected = NoFeedBackMessageFound.class)
    public void testGetFeedbackMessageByClientOrderIdWhenThereIsNoFeedbackMessageWithWantedId_shouldThrowException() {
        feedbackMessageService.getFeedbackMessageByClientOrderId(NUMBER_OF_PREINSERTED_FEEDBACK_MESSAGES + 1);
    }

    @Test
    public void testGetFeedbackMessageByClientOrderId_shouldBeSuccessful() {
        FeedbackMessage feedbackMessage = feedbackMessageService.getFeedbackMessageByClientOrderId(PREINSERTED_CLIENT_ORDER_1.getId());
        assertThat(feedbackMessage, is(PREINSERTED_FEEDBACK_MESSAGE_1));
    }
}
