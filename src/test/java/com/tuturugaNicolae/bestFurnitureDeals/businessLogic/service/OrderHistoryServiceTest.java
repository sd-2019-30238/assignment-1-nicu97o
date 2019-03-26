package com.tuturugaNicolae.bestFurnitureDeals.businessLogic.service;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.configuration.BusinessLogicConfiguration;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.exception.NoOrderHistoryFoundException;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.SecurityContext;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.OrderHistoryService;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.OrderHistoryDAO;
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

import java.time.LocalDateTime;

import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BusinessLogicConfiguration.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class OrderHistoryServiceTest {
    @Autowired
    private SecurityContext securityContext;
    @Autowired
    private OrderHistoryService orderHistoryService;
    @Autowired
    private OrderHistoryDAO orderHistoryDAO;

    @Before
    public void setUp() {
        securityContext.authenticate(PREINSERTED_USER_2.getUsername(), PREINSERTED_USER_2.getPassword());
    }

//    @Test
//    public void testUpdateOrderHistoryState_shouldBeSuccessful() {
//        orderHistoryService.updateOrderHistoryState(PREINSERTED_ORDER_HISTORY_1);
//        OrderHistory orderHistory = orderHistoryService.getOrderHistoryById(PREINSERTED_ORDER_HISTORY_1.getId());
//        assertThat(orderHistory.getOrderState(), is(OrderState.ACCEPTED));
//    }

    @Test
    public void testGetOrderHistoryBasedOnClientOrder_shouldBeSuccessful() {
        OrderHistory orderHistory = orderHistoryService.getOrderHistoryBasedOnClientOrder(PREINSERTED_CLIENT_ORDER_1);
        assertThat(orderHistory, is(PREINSERTED_ORDER_HISTORY_1));
    }

    @Test
    public void testAddNewOrderHistory_shouldBeSuccessful() {
        orderHistoryService.addNewOrderHistory(new OrderHistory(null, PREINSERTED_CLIENT_ORDER_WITHOUT_ORDER_HISTORY, LocalDateTime.now(), OrderState.PIKING));
        assertTrue(orderHistoryDAO.selectAll().size() == NUMBER_OF_PREINSERTED_ORDER_HISTORY + 1);
    }

    @Test
    public void testPlaceOrder_shouldBeSuccessful() {
        orderHistoryService.placeOrder(PREINSERTED_ORDER_HISTORY_2);
        OrderHistory orderHistory = orderHistoryService.getOrderHistoryById(PREINSERTED_ORDER_HISTORY_2.getId());
        assertTrue(orderHistory.getOrderState().equals(OrderState.PLACED));
    }

    @Test(expected = NoOrderHistoryFoundException.class)
    public void testGetOrderHistoryById_shouldBeSuccessful() {
        orderHistoryService.getOrderHistoryById(NUMBER_OF_PREINSERTED_ORDER_HISTORY + 1);
    }

    @Test
    public void testGetOrderHistoryByIdWhenNoOrderHistoryWithWantedIdExists_shouldThrowException() {
        OrderHistory orderHistory = orderHistoryService.getOrderHistoryById(PREINSERTED_ORDER_HISTORY_1.getId());
        assertThat(orderHistory, is(PREINSERTED_ORDER_HISTORY_1));
    }
}
