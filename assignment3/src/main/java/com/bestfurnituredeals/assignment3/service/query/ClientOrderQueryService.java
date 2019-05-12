package com.bestfurnituredeals.assignment3.service.query;

import com.bestfurnituredeals.assignment3.model.database.ClientOrder;

import java.util.List;

public interface ClientOrderQueryService {
    ClientOrder getClientOrderById(long id);

    ClientOrder getCurrentClientOrderForAnUser(String loggedUser);

    List<ClientOrder> getAllOrders();

    List<ClientOrder> getAllUnapprovedClientOrders();

    List<ClientOrder> getOrdersForAnUser(String loggedUser);
}
