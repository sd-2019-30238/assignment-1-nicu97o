package com.bestfurnituredeals.assignment3.service.command;

import com.bestfurnituredeals.assignment3.model.database.ClientOrder;

import javax.mail.MessagingException;

public interface ClientOrderCommandService {
    ClientOrder addClientOrder(ClientOrder clientOrder, String loggedUser);

    void updateOrderState(long id) throws MessagingException, javax.mail.MessagingException;

    void checkoutCurrentOrder(String username, boolean subscribe);

    void approveOrder(long orderId) throws MessagingException, javax.mail.MessagingException;
}
