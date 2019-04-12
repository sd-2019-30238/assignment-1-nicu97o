package com.bestFurnitureDeals.service.impl;

import com.bestFurnitureDeals.dao.ClientOrderDAO;
import com.bestFurnitureDeals.exception.NoClientOrderFoundException;
import com.bestFurnitureDeals.exception.OrderNotPlacedException;
import com.bestFurnitureDeals.model.ClientOrder;
import com.bestFurnitureDeals.model.OrderHistory;
import com.bestFurnitureDeals.model.OrderState;
import com.bestFurnitureDeals.model.PaymentMethod;
import com.bestFurnitureDeals.service.ClientOrderService;
import com.bestFurnitureDeals.service.UserService;
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
    private UserService userService;

    @Autowired
    public ClientOrderServiceImpl(ClientOrderDAO clientOrderDAO, UserService userService) {
        this.clientOrderDAO = clientOrderDAO;
        this.userService = userService;
    }

    @Override
    public ClientOrder getClientOrderById(long id) {
        Optional<ClientOrder> clientOrder = clientOrderDAO.findById(id);
        if (!clientOrder.isPresent()) {
            throw new NoClientOrderFoundException("No client order found for user with id " + id + " found!");
        }
        return clientOrder.get();
    }

    @Override
    public ClientOrder addClientOrder(ClientOrder clientOrder, String loggedUser) {
        OrderHistory orderHistory = new OrderHistory(LocalDateTime.now(), OrderState.PIKING);
        clientOrder.setOrderHistory(orderHistory);
        orderHistory.setClientOrder(clientOrder);
        clientOrder.setUser(userService.getUserByUsername(loggedUser));
        clientOrderDAO.save(clientOrder);
        return clientOrder;
    }

    @Override
    public ClientOrder getCurrentClientOrderForAnUser(String loggedUser) {
        Optional<ClientOrder> clientOrder = clientOrderDAO.findClientOrderByUserUsernameAndAndFinishedFalse(loggedUser);
        if (clientOrder.isPresent()) {
            return clientOrder.get();
        }
        return addClientOrder(new ClientOrder(false, PaymentMethod.CASH, BigDecimal.ZERO, false), loggedUser);
    }

    @Override
    public List<ClientOrder> getOrdersForAnUser(String loggedUser) {
        return clientOrderDAO.findClientOrdersByUserUsername(loggedUser);
    }

    @Override
    public void updateOrderState(long id) {
        ClientOrder clientOrder = getClientOrderById(id);
        if (clientOrder.getOrderHistory().equals(OrderState.PIKING)) {
            throw new OrderNotPlacedException();
        } else if (clientOrder.getOrderHistory().equals(OrderState.PLACED)) {
            clientOrder.setApproved(true);
            clientOrder.getOrderHistory().setOrderState(OrderState.ACCEPTED);
        } else if (clientOrder.getOrderHistory().equals(OrderState.ACCEPTED)) {
            clientOrder.getOrderHistory().setOrderState(OrderState.DELIVERED);
        } else if (clientOrder.getOrderHistory().equals(OrderState.DELIVERED)) {
            clientOrder.getOrderHistory().setOrderState(OrderState.COMPLETED);
        }
        clientOrderDAO.save(clientOrder);
    }

    @Override
    public void checkoutCurrentOrder(String username) {
        ClientOrder clientOrder = getCurrentClientOrderForAnUser(username);
        clientOrder.setFinished(true);
        clientOrder.getOrderHistory().setOrderPlaceDateTime(LocalDateTime.now());
        clientOrder.getOrderHistory().setOrderState(OrderState.PLACED);
        clientOrderDAO.save(clientOrder);
    }
}
