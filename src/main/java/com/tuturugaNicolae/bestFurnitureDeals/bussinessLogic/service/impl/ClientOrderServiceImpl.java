package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.IsStaff;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.ClientOrderService;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.OrderHistoryService;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.ClientOrderDAO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.*;
import com.tuturugaNicolae.bestFurnitureDeals.exception.ForbiddenException;
import com.tuturugaNicolae.bestFurnitureDeals.exception.NoClientOrderFoundException;
import com.tuturugaNicolae.bestFurnitureDeals.exception.NoRightsToPerformThisOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientOrderServiceImpl implements ClientOrderService {
    private ClientOrderDAO clientOrderDAO;
    private OrderHistoryService orderHistoryService;

    @Autowired
    public ClientOrderServiceImpl(ClientOrderDAO clientOrderDAO, OrderHistoryService orderHistoryService) {
        this.clientOrderDAO = clientOrderDAO;
        this.orderHistoryService = orderHistoryService;
    }

    @Override
    public ClientOrder addClientOrder(ClientOrder clientOrder, User loggedUser) {
        clientOrder.setClient(loggedUser);
        clientOrderDAO.insert(clientOrder);
        OrderHistory orderHistory = new OrderHistory(0L, clientOrder, LocalDateTime.now(), OrderState.PIKING);
        orderHistoryService.addNewOrderHistory(orderHistory);
        return clientOrder;
    }

    @Override
    public void updateClientOrder(ClientOrder clientOrder, User loggedUser) {
        if (!clientOrder.getClient().getUsername().equals(loggedUser.getUsername())) {
            throw new ForbiddenException();
        }
        ClientOrder oldClientOrder = getClientOrderById(clientOrder.getId());
        oldClientOrder.setTotalPrice(clientOrder.getTotalPrice());
        oldClientOrder.setFinished(clientOrder.isFinished());
        clientOrderDAO.update(oldClientOrder);
    }

    @Override
    public void deleteClientOrder(ClientOrder clientOrder) {
        clientOrderDAO.delete(getClientOrderById(clientOrder.getId()));
    }

    @Override
    @IsStaff
    public List<ClientOrder> getAllClientOrders() {
        List<ClientOrder> clientOrders = clientOrderDAO.selectAll();
        if (clientOrders.isEmpty()) {
            throw new NoClientOrderFoundException();
        }
        return clientOrders;
    }

    @Override
    public ClientOrder getClientOrderById(long id) {
        ClientOrder clientOrder = clientOrderDAO.selectById(id);
        if (clientOrder == null) {
            throw new NoClientOrderFoundException();
        }
        return clientOrder;
    }

    @Override
    public List<ClientOrder> getAllFinishedOrdersForAnUser(String username) {
        List<ClientOrder> clientOrders = clientOrderDAO.findAllFinishedOrdersForAnUser(username);
        if (clientOrders.isEmpty()) {
            throw new NoClientOrderFoundException();
        }
        return clientOrders;
    }

    @Override
    public ClientOrder getCurrentClientOrderForAnUser(User loggedUser) {
        Optional<ClientOrder> clientOrder = clientOrderDAO.findClientOrderByUser(loggedUser.getUsername());
        if (!clientOrder.isPresent()) {
            ClientOrder newClientOrder = new ClientOrder(0L, false, PaymentMethod.CASH, BigDecimal.ZERO, false);
            return addClientOrder(newClientOrder, loggedUser);
        }
        return clientOrder.get();
    }

    @Override
    @IsStaff
    public void approveClientOrder(ClientOrder clientOrder) {
        if (!clientOrder.isFinished() || clientOrder.getOrderHistory().getOrderState() != OrderState.PLACED) {
            throw new NoRightsToPerformThisOperationException("Can't approve this order!");
        }
        clientOrder.setApproved(true);
        clientOrderDAO.update(clientOrder);
    }

    @Override
    @IsStaff
    public List<ClientOrder> getAllFinishedClientOrders() {
        List<ClientOrder> clientOrders = clientOrderDAO.getAllFinishedOrders();
        if (clientOrders.isEmpty()) {
            throw new NoClientOrderFoundException();
        }
        return clientOrders;
    }
}
