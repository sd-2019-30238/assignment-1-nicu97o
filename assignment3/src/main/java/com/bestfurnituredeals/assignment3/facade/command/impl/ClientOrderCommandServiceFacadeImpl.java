package com.bestfurnituredeals.assignment3.facade.command.impl;

import com.bestfurnituredeals.assignment3.facade.command.ClientOrderCommandServiceFacade;
import com.bestfurnituredeals.assignment3.service.command.ClientOrderCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class ClientOrderCommandServiceFacadeImpl implements ClientOrderCommandServiceFacade {
    private ClientOrderCommandService clientOrderCommandService;

    @Autowired
    public ClientOrderCommandServiceFacadeImpl(ClientOrderCommandService clientOrderCommandService) {
        this.clientOrderCommandService = clientOrderCommandService;
    }

    @Override
    public void updateOrderState(long id) throws MessagingException, MessagingException {
        clientOrderCommandService.updateOrderState(id);
    }

    @Override
    public void checkoutCurrentOrder(String username, boolean subscribe) {
        clientOrderCommandService.checkoutCurrentOrder(username, subscribe);
    }

    @Override
    public void approveOrder(long orderId) throws MessagingException, MessagingException {
        clientOrderCommandService.approveOrder(orderId);
    }
}
