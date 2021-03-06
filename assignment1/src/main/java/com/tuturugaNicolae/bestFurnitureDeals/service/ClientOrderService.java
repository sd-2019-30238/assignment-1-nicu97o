package com.tuturugaNicolae.bestFurnitureDeals.service;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.User;

import java.util.List;

public interface ClientOrderService {
    ClientOrder addClientOrder(ClientOrder clientOrder, User loggedUser);

    void updateClientOrder(ClientOrder clientOrder, User loggedUser);

    void deleteClientOrder(ClientOrder clientOrder);

    List<ClientOrder> getAllClientOrders();

    ClientOrder getClientOrderById(long id);

    List<ClientOrder> getAllFinishedOrdersForAnUser(String username);

    ClientOrder getCurrentClientOrderForAnUser(User loggedUser);

    void approveClientOrder(ClientOrder clientOrder);

    List<ClientOrder> getAllFinishedClientOrders();
}
