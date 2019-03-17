package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess;

import com.tuturugaNicolae.bestFurnitureDeals.model.FeedbackMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class FeedbackMessageDAOTest extends GenericDAOTest<FeedbackMessage> {
    @Autowired
    private FeedbackMessageDAO feedbackMessageDAO;

    @Test
    public void testSelectAll_shouldBeSuccessful() {
        testSelectAll(feedbackMessageDAO, NUMBER_OF_PREINSERTED_FEEDBACK_MESSAGES);
    }

    @Test
    public void testSelectById_shouldBeSuccessful() {
        testSelectById(feedbackMessageDAO, PREINSERTED_FEEDBACK_MESSAGE_1.getId(), PREINSERTED_FEEDBACK_MESSAGE_1);
    }

    @Test
    public void testInsertShouldBeSuccessful() {
        FeedbackMessage feedbackMessage = new FeedbackMessage(0L, "Test tile", "Test desc", PREINSERTED_USER_1);
        testInsert(feedbackMessageDAO, feedbackMessage, NUMBER_OF_PREINSERTED_FEEDBACK_MESSAGES + 1);
    }

    @Test
    public void testUpdate_shouldBeSuccessful() {
        FeedbackMessage feedbackMessage = new FeedbackMessage(PREINSERTED_FEEDBACK_MESSAGE_1.getId(), "Test tile", "Test desc", PREINSERTED_USER_1);
        testUpdate(feedbackMessageDAO, feedbackMessage, PREINSERTED_FEEDBACK_MESSAGE_1.getId());
    }

    @Test
    public void testDelete_shouldBeSuccessful() {
        FeedbackMessage feedbackMessage = feedbackMessageDAO.selectById(PREINSERTED_FEEDBACK_MESSAGE_1.getId());
        testDelete(feedbackMessageDAO, feedbackMessage, NUMBER_OF_PREINSERTED_FEEDBACK_MESSAGES - 1);
    }
}
