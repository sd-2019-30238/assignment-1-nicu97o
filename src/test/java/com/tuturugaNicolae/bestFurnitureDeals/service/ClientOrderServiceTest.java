package com.tuturugaNicolae.bestFurnitureDeals.service;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.configuration.BusinessLogicConfiguration;
import com.tuturugaNicolae.bestFurnitureDeals.exception.NoClientOrderFoundException;
import com.tuturugaNicolae.bestFurnitureDeals.exception.NoRightsToPerformThisOperationException;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.SecurityContext;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BusinessLogicConfiguration.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class ClientOrderServiceTest {
    @Autowired
    private SecurityContext securityContext;
    @Autowired
    private ClientOrderService clientOrderService;

    @Before
    public void setUp() {
        securityContext.authenticate(PREINSERTED_USER_2.getUsername(), PREINSERTED_USER_2.getPassword());
    }

    @Test
    public void testAddClientOrder_shouldBeSuccessful() {
        clientOrderService.addClientOrder(new ClientOrder(), securityContext.getLoggedUser().get());
        assertThat(clientOrderService.getAllClientOrders().size(), is(NUMBER_OF_PREINSERTED_CLIENT_ORDERS + 1));
    }

    @Test
    public void testUpdateClientOrder_shouldBeSuccessful() {
        ClientOrder clientOrder = clientOrderService.getClientOrderById(PREINSERTED_CLIENT_ORDER_WITHOUT_ORDER_HISTORY.getId());
        clientOrder.setTotalPrice(BigDecimal.ZERO);
        clientOrder.setFinished(!PREINSERTED_CLIENT_ORDER_WITHOUT_ORDER_HISTORY.isFinished());
        clientOrderService.updateClientOrder(clientOrder, securityContext.getLoggedUser().get());
        assertThat(clientOrderService.getClientOrderById(clientOrder.getId()), is(clientOrder));
    }

    @Test
    public void testDeleteClientOrder_shouldBeSuccessful() {
        clientOrderService.deleteClientOrder(PREINSERTED_CLIENT_ORDER_1);
        assertThat(clientOrderService.getAllClientOrders().size(), is(NUMBER_OF_PREINSERTED_CLIENT_ORDERS - 1));
    }

    @Test
    public void testGetAllClientOrders_shouldBeSuccessful() {
        assertThat(clientOrderService.getAllClientOrders().size(), is(NUMBER_OF_PREINSERTED_CLIENT_ORDERS));
    }

    @Test
    public void testGetClientOrderById_shouldBeSuccessful() {
        ClientOrder clientOrder = clientOrderService.getClientOrderById(PREINSERTED_CLIENT_ORDER_1.getId());
        assertThat(clientOrder, is(PREINSERTED_CLIENT_ORDER_1));
    }

    @Test(expected = NoClientOrderFoundException.class)
    public void testGetClientOrderByIdWhenNoClientOrderFound_shouldThrowException() {
        clientOrderService.getClientOrderById(NUMBER_OF_PREINSERTED_CLIENT_ORDERS + 1);
    }

    @Test
    public void testGetAllFinishedOrdersForAnUser_shouldBeSuccessful() {
        List<ClientOrder> clientOrders = clientOrderService.getAllFinishedOrdersForAnUser(PREINSERTED_USER_1.getUsername());
        assertTrue(clientOrders.contains(PREINSERTED_CLIENT_ORDER_1));
        assertTrue(!clientOrders.contains(PREINSERTED_CLIENT_ORDER_2));
    }

    @Test
    public void testGetCurrentClientOrderForAnUser_shouldBeSuccessful() {
        ClientOrder clientOrder = clientOrderService.getCurrentClientOrderForAnUser(PREINSERTED_USER_1);
        assertThat(clientOrder, is(PREINSERTED_CLIENT_ORDER_2));
    }

    @Test
    public void testApproveClientOrder_shouldBeSuccessful() {
        clientOrderService.approveClientOrder(clientOrderService.getClientOrderById(PREINSERTED_CLIENT_ORDER_1.getId()));
        ClientOrder clientOrder = clientOrderService.getClientOrderById(PREINSERTED_CLIENT_ORDER_1.getId());
        assertTrue(clientOrder.isApproved());
    }

    @Test(expected = NoRightsToPerformThisOperationException.class)
    public void testApproveClientOrderWhenUserHasntFinished_shouldThrowException() {
        clientOrderService.approveClientOrder(PREINSERTED_CLIENT_ORDER_2);
    }

    @Test
    public void testGetAllFinishedClientOrders_shouldBeSuccessful() {
        List<ClientOrder> clientOrders = clientOrderService.getAllFinishedClientOrders();
        assertTrue(clientOrders.contains(PREINSERTED_CLIENT_ORDER_1));
        assertTrue(!clientOrders.contains(PREINSERTED_CLIENT_ORDER_2));
        assertTrue(!clientOrders.contains(PREINSERTED_CLIENT_ORDER_WITHOUT_ORDER_HISTORY));
    }
}
