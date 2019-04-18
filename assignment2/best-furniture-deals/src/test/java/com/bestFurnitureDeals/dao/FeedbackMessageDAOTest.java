package com.bestFurnitureDeals.dao;

import com.bestFurnitureDeals.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FeedbackMessageDAOTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private FeedbackMessageDAO feedbackMessageDAO;

    @Test
    public void testFindFeedbackMessageByOrderHistoryId_shouldBeSuccessful() {
        ClientOrder clientOrder = new ClientOrder(null, true, PaymentMethod.CASH, BigDecimal.ZERO, true, null, new ArrayList<>(), null);
        entityManager.persist(clientOrder);
        OrderHistory orderHistory = new OrderHistory(null, LocalDateTime.now(), OrderState.COMPLETED, clientOrder, null);
        entityManager.persist(orderHistory);
        FeedbackMessage feedbackMessage = new FeedbackMessage(null, "test", "body message", orderHistory);
        entityManager.persist(feedbackMessage);
        entityManager.flush();
        FeedbackMessage foundFeedbackMessage = feedbackMessageDAO.findFeedbackMessageByOrderHistoryId(clientOrder.getId()).get();
        assertThat(foundFeedbackMessage, is(feedbackMessage));
    }
}
