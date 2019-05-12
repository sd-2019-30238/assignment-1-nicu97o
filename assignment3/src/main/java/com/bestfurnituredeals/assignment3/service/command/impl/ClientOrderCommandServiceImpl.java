package com.bestfurnituredeals.assignment3.service.command.impl;


import com.bestfurnituredeals.assignment3.dao.ClientOrderDAO;
import com.bestfurnituredeals.assignment3.exception.NoProductFoundException;
import com.bestfurnituredeals.assignment3.exception.OrderNotApprovedException;
import com.bestfurnituredeals.assignment3.exception.OrderNotPlacedException;
import com.bestfurnituredeals.assignment3.model.database.ClientOrder;
import com.bestfurnituredeals.assignment3.model.database.OrderHistory;
import com.bestfurnituredeals.assignment3.model.database.OrderState;
import com.bestfurnituredeals.assignment3.model.database.UserObserver;
import com.bestfurnituredeals.assignment3.service.command.ClientOrderCommandService;
import com.bestfurnituredeals.assignment3.service.command.ObserverService;
import com.bestfurnituredeals.assignment3.service.query.ClientOrderQueryService;
import com.bestfurnituredeals.assignment3.service.query.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class ClientOrderCommandServiceImpl implements ClientOrderCommandService {
    private ClientOrderDAO clientOrderDAO;
    private UserQueryService userQueryService;
    private ObserverService<UserObserver, ClientOrder> observerService;
    private ClientOrderQueryService clientOrderQueryService;

    @Autowired
    public ClientOrderCommandServiceImpl(ClientOrderDAO clientOrderDAO, UserQueryService userQueryService, ObserverService<UserObserver, ClientOrder> observerService, @Lazy ClientOrderQueryService clientOrderQueryService) {
        this.clientOrderDAO = clientOrderDAO;
        this.userQueryService = userQueryService;
        this.observerService = observerService;
        this.clientOrderQueryService = clientOrderQueryService;
    }

    @Override
    public ClientOrder addClientOrder(ClientOrder clientOrder, String loggedUser) {
        OrderHistory orderHistory = new OrderHistory(LocalDateTime.now(), OrderState.PIKING);
        clientOrder.setOrderHistory(orderHistory);
        orderHistory.setClientOrder(clientOrder);
        clientOrder.setUser(userQueryService.getUserByUsername(loggedUser));
        clientOrderDAO.save(clientOrder);
        return clientOrder;
    }

    @Override
    @PreAuthorize("hasAuthority('STAFF')")
    public void updateOrderState(long id) throws MessagingException {
        ClientOrder clientOrder = clientOrderQueryService.getClientOrderById(id);
        if (clientOrder.getOrderHistory().getOrderState().equals(OrderState.PIKING)) {
            throw new OrderNotPlacedException();
        } else if (!clientOrder.isApproved()) {
            throw new OrderNotApprovedException();
        } else if (clientOrder.getOrderHistory().getOrderState().equals(OrderState.ACCEPTED)) {
            clientOrder.getOrderHistory().setOrderState(OrderState.DELIVERED);
        } else if (clientOrder.getOrderHistory().getOrderState().equals(OrderState.DELIVERED)) {
            clientOrder.getOrderHistory().setOrderState(OrderState.COMPLETED);
        }
        clientOrderDAO.save(clientOrder);
        observerService.notifyObservers(clientOrder.getId());
    }

    @Override
    public void checkoutCurrentOrder(String username, boolean subscribe) {
        ClientOrder clientOrder = clientOrderQueryService.getCurrentClientOrderForAnUser(username);
        if (clientOrder.getProducts().isEmpty()) {
            throw new NoProductFoundException("No products added for current order!");
        }
        if (subscribe) {
            observerService.addObserver(clientOrder.getId(), new UserObserver(0L, userQueryService.getUserByUsername(username).getMail()));
        }
        clientOrder.setFinished(true);
        clientOrder.getOrderHistory().setOrderPlaceDateTime(LocalDateTime.now());
        clientOrder.getOrderHistory().setOrderState(OrderState.PLACED);
        clientOrderDAO.save(clientOrder);
    }

    @Override
    @PreAuthorize("hasAuthority('STAFF')")
    public void approveOrder(long orderId) throws MessagingException {
        ClientOrder clientOrder = clientOrderQueryService.getClientOrderById(orderId);
        if (!clientOrder.isFinished()) {
            throw new OrderNotPlacedException();
        }
        clientOrder.setApproved(true);
        clientOrder.getOrderHistory().setOrderState(OrderState.ACCEPTED);
        clientOrderDAO.save(clientOrder);
        observerService.notifyObservers(clientOrder.getId());
    }
}
