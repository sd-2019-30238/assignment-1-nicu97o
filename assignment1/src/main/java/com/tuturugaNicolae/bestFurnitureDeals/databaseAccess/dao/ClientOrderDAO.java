package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;

import java.util.List;
import java.util.Optional;

public interface ClientOrderDAO extends GenericDAO<ClientOrder> {
    Optional<ClientOrder> findClientOrderByUser(String username);

    List<ClientOrder> findAllFinishedOrdersForAnUser(String username);

    List<ClientOrder> getAllFinishedOrders();
}
