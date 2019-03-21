package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess;

import com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer;
import com.tuturugaNicolae.bestFurnitureDeals.model.ClientOrder;
import com.tuturugaNicolae.bestFurnitureDeals.model.OrderHistory;
import com.tuturugaNicolae.bestFurnitureDeals.model.PaymentMethod;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ClientOrderDAOTest extends GenericDAOTest<ClientOrder> {
    @Autowired
    private ClientOrderDAO clientOrderDAO;

    @Test
    public void testSelectAll_shouldBeSuccessful() {
        testSelectAll(clientOrderDAO, PreinsertedDataContainer.NUMBER_OF_PREINSERTED_CLIENT_ORDERS);
    }

    @Test
    public void testSelectById_shouldBeSuccessful() {
        testSelectById(clientOrderDAO, PreinsertedDataContainer.PREINSERTED_CLIENT_ORDER_1.getId(), PreinsertedDataContainer.PREINSERTED_CLIENT_ORDER_1);
    }

    @Test
    public void testInsertShouldBeSuccessful() {
        ClientOrder clientOrder = new ClientOrder(0L, true, PaymentMethod.CASH, BigDecimal.TEN, true, new ArrayList<>(), PreinsertedDataContainer.PREINSERTED_USER_1, new OrderHistory());
        testInsert(clientOrderDAO, clientOrder, PreinsertedDataContainer.NUMBER_OF_PREINSERTED_CLIENT_ORDERS + 1);
    }

    @Test
    public void testUpdate_shouldBeSuccessful() {
        ClientOrder clientOrder = new ClientOrder(PreinsertedDataContainer.PREINSERTED_CLIENT_ORDER_1.getId(), true, PaymentMethod.CASH, BigDecimal.TEN, true, new ArrayList<>(), PreinsertedDataContainer.PREINSERTED_USER_1, new OrderHistory());
        testUpdate(clientOrderDAO, clientOrder, PreinsertedDataContainer.PREINSERTED_CLIENT_ORDER_1.getId());
    }

    @Test
    public void testDelete_shouldBeSuccessful() {
        testDelete(clientOrderDAO, PreinsertedDataContainer.PREINSERTED_CLIENT_ORDER_1.getId(), PreinsertedDataContainer.NUMBER_OF_PREINSERTED_CLIENT_ORDERS - 1);
    }
}
