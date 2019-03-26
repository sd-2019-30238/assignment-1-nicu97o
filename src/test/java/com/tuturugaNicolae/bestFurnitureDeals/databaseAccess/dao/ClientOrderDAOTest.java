package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao;

import com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.OrderHistory;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.PaymentMethod;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void testFindClientOrderByUser_shouldBeSuccessful(){
        ClientOrder clientOrder = clientOrderDAO.findClientOrderByUser(PreinsertedDataContainer.PREINSERTED_USER_1.getUsername()).get();
        assertThat(clientOrder, is(PreinsertedDataContainer.PREINSERTED_CLIENT_ORDER_2));
    }

    @Test
    public void testFindAllFinishedOrdersForAnUser_shouldBeSuccessful(){
        List<ClientOrder> clientOrders = clientOrderDAO.findAllFinishedOrdersForAnUser(PreinsertedDataContainer.PREINSERTED_USER_1.getUsername());
        assertTrue(clientOrders.contains(PreinsertedDataContainer.PREINSERTED_CLIENT_ORDER_1));
        assertTrue(!clientOrders.contains(PreinsertedDataContainer.PREINSERTED_CLIENT_ORDER_2));
    }

    @Test
    public void testGetAllFinishedOrders_shouldBeSuccessful(){
        List<ClientOrder> clientOrders = clientOrderDAO.getAllFinishedOrders();
        assertTrue(clientOrders.contains(PreinsertedDataContainer.PREINSERTED_CLIENT_ORDER_1));
        assertTrue(!clientOrders.contains(PreinsertedDataContainer.PREINSERTED_CLIENT_ORDER_2));
    }
}
