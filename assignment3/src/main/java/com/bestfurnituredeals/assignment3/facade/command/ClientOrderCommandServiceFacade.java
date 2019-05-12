package com.bestfurnituredeals.assignment3.facade.command;

import javax.mail.MessagingException;

public interface ClientOrderCommandServiceFacade {
    void updateOrderState(long id) throws MessagingException, javax.mail.MessagingException;

    void checkoutCurrentOrder(String username, boolean subscribe);

    void approveOrder(long orderId) throws MessagingException, javax.mail.MessagingException;
}
