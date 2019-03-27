package com.tuturugaNicolae.bestFurnitureDeals.service;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.OrderHistory;

public interface OrderHistoryService {
    void updateOrderHistoryState(OrderHistory orderHistory);

    OrderHistory getOrderHistoryBasedOnClientOrder(ClientOrder clientOrder);

    void addNewOrderHistory(OrderHistory orderHistory);

    void placeOrder(OrderHistory orderHistory);

    OrderHistory getOrderHistoryById(long id);
}
