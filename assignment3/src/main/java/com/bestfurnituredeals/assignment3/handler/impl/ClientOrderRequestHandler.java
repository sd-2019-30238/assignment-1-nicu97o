package com.bestfurnituredeals.assignment3.handler.impl;

import com.bestfurnituredeals.assignment3.handler.RequestHandler;
import com.bestfurnituredeals.assignment3.model.write.CheckoutCommandDTO;
import com.bestfurnituredeals.assignment3.request.Request;
import com.bestfurnituredeals.assignment3.service.command.ClientOrderCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class ClientOrderRequestHandler implements RequestHandler {
    private ClientOrderCommandService clientOrderCommandService;

    @Autowired
    public ClientOrderRequestHandler(ClientOrderCommandService clientOrderCommandService) {
        this.clientOrderCommandService = clientOrderCommandService;
    }

    @Override
    public void handleRequest(Request request) {
        if (request.getRequestName().equals("checkout")) {
            checkoutCurrentOrder(request);
        } else if (request.getRequestName().equals("approveOrder")) {
            approveOrder(request);
        } else if (request.getRequestName().equals("updateState")) {
            updateUpdateOrderState(request);
        }
    }

    private void updateUpdateOrderState(Request request) {
        try {
            clientOrderCommandService.updateOrderState((Long) request.getRequestObject());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void checkoutCurrentOrder(Request request) {
        String username = ((CheckoutCommandDTO) request.getRequestObject()).getUsername();
        boolean subscribe = ((CheckoutCommandDTO) request.getRequestObject()).isSubscribe();
        clientOrderCommandService.checkoutCurrentOrder(username, subscribe);
    }

    private void approveOrder(Request request) {
        try {
            clientOrderCommandService.approveOrder((Long) request.getRequestObject());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
