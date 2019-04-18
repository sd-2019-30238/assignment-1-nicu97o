package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao;

import com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.FeedbackMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FeedbackMessageDAOTest extends GenericDAOTest<FeedbackMessage> {
    @Autowired
    private FeedbackMessageDAO feedbackMessageDAO;

    @Test
    public void testSelectAll_shouldBeSuccessful() {
        testSelectAll(feedbackMessageDAO, PreinsertedDataContainer.NUMBER_OF_PREINSERTED_FEEDBACK_MESSAGES);
    }

    @Test
    public void testSelectById_shouldBeSuccessful() {
        testSelectById(feedbackMessageDAO, PreinsertedDataContainer.PREINSERTED_FEEDBACK_MESSAGE_1.getId(), PreinsertedDataContainer.PREINSERTED_FEEDBACK_MESSAGE_1);
    }

    @Test
    public void testInsertShouldBeSuccessful() {
        FeedbackMessage feedbackMessage = new FeedbackMessage(0L, "Test tile", "Test desc");
        testInsert(feedbackMessageDAO, feedbackMessage, PreinsertedDataContainer.NUMBER_OF_PREINSERTED_FEEDBACK_MESSAGES + 1);
    }

    @Test
    public void testUpdate_shouldBeSuccessful() {
        FeedbackMessage feedbackMessage = new FeedbackMessage(PreinsertedDataContainer.PREINSERTED_FEEDBACK_MESSAGE_1.getId(), "Test tile", "Test desc");
        testUpdate(feedbackMessageDAO, feedbackMessage, PreinsertedDataContainer.PREINSERTED_FEEDBACK_MESSAGE_1.getId());
    }

    @Test
    public void testDelete_shouldBeSuccessful() {
        testDelete(feedbackMessageDAO, PreinsertedDataContainer.PREINSERTED_FEEDBACK_MESSAGE_1.getId(), PreinsertedDataContainer.NUMBER_OF_PREINSERTED_FEEDBACK_MESSAGES - 1);
    }

    @Test
    public void testGetFeedbackMessageByClientOrderId_shouldBeSuccessful(){
        FeedbackMessage feedbackMessage = feedbackMessageDAO.getFeedbackMessageByClientOrderId(PreinsertedDataContainer.PREINSERTED_CLIENT_ORDER_1.getId()).get();
        assertThat(feedbackMessage, is(PreinsertedDataContainer.PREINSERTED_FEEDBACK_MESSAGE_1));
    }
}
