package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess;

import com.tuturugaNicolae.bestFurnitureDeals.model.OrderHistory;
import com.tuturugaNicolae.bestFurnitureDeals.model.OrderState;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class OrderHistoryDAOTest extends GenericDAOTest<OrderHistory> {
    @Autowired
    private OrderHistoryDAO orderHistoryDAO;

    @Test
    public void testSelectAll_shouldBeSuccessful() {
        testSelectAll(orderHistoryDAO, NUMBER_OF_PREINSERTED_ORDER_HISTORY);
    }

    @Test
    public void testSelectById_shouldBeSuccessful() {
        testSelectById(orderHistoryDAO, PREINSERTED_ORDER_HISTORY_1.getId(), PREINSERTED_ORDER_HISTORY_1);
    }

    @Test
    public void testInsertShouldBeSuccessful() {
        OrderHistory orderHistory = new OrderHistory(0L, PREINSERTED_CLIENT_ORDER_WITHOUT_ORDER_HISTORY, LocalDateTime.now(), OrderState.PLACED);
        testInsert(orderHistoryDAO, orderHistory, NUMBER_OF_PREINSERTED_ORDER_HISTORY + 1);
    }

    @Test
    public void testUpdate_shouldBeSuccessful() {
        OrderHistory orderHistory = new OrderHistory(1L, LocalDateTime.now(), OrderState.PIKED_UP);
        testUpdate(orderHistoryDAO, orderHistory, PREINSERTED_ORDER_HISTORY_1.getId());
    }

    @Test
    public void testDelete_shouldBeSuccessful() {
        OrderHistory orderHistory = orderHistoryDAO.selectById(PREINSERTED_ORDER_HISTORY_1.getId());
        testDelete(orderHistoryDAO, orderHistory, NUMBER_OF_PREINSERTED_ORDER_HISTORY - 1);
    }
}
