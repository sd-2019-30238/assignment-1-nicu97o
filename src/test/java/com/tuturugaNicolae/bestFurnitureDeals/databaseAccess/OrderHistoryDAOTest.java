package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess;

import com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer;
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
        testSelectAll(orderHistoryDAO, PreinsertedDataContainer.NUMBER_OF_PREINSERTED_ORDER_HISTORY);
    }

    @Test
    public void testSelectById_shouldBeSuccessful() {
        testSelectById(orderHistoryDAO, PreinsertedDataContainer.PREINSERTED_ORDER_HISTORY_1.getId(), PreinsertedDataContainer.PREINSERTED_ORDER_HISTORY_1);
    }

    @Test
    public void testInsertShouldBeSuccessful() {
        OrderHistory orderHistory = new OrderHistory(0L, PreinsertedDataContainer.PREINSERTED_CLIENT_ORDER_WITHOUT_ORDER_HISTORY, LocalDateTime.now(), OrderState.PLACED);
        testInsert(orderHistoryDAO, orderHistory, PreinsertedDataContainer.NUMBER_OF_PREINSERTED_ORDER_HISTORY + 1);
    }

    @Test
    public void testUpdate_shouldBeSuccessful() {
        OrderHistory orderHistory = new OrderHistory(1L, LocalDateTime.now(), OrderState.ACCEPTED);
        testUpdate(orderHistoryDAO, orderHistory, PreinsertedDataContainer.PREINSERTED_ORDER_HISTORY_1.getId());
    }

    @Test
    public void testDelete_shouldBeSuccessful() {
        testDelete(orderHistoryDAO, PreinsertedDataContainer.PREINSERTED_ORDER_HISTORY_1.getId(), PreinsertedDataContainer.NUMBER_OF_PREINSERTED_ORDER_HISTORY - 1);
    }
}
