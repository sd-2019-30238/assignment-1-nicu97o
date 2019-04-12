package com.bestFurnitureDeals.service;

import com.bestFurnitureDeals.model.ClientOrder;

import java.util.List;

public interface ClientOrderService {
    ClientOrder getClientOrderById(long id);

    ClientOrder addClientOrder(ClientOrder clientOrder, String loggedUser);

    ClientOrder getCurrentClientOrderForAnUser(String loggedUser);

    List<ClientOrder> getAllOrders();

    List<ClientOrder> getAllUnapprovedClientOrders();

    List<ClientOrder> getOrdersForAnUser(String loggedUser);

    void updateOrderState(long id);

    void checkoutCurrentOrder(String username);

    void approveOrder(long orderId);
}
