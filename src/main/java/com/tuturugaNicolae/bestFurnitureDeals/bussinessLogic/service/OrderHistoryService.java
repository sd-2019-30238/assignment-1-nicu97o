package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.OrderHistory;

public interface OrderHistoryService {
    void updateOrderHistoryState(OrderHistory orderHistory);

    OrderHistory getOrderHistoryBasedOnClientOrder(ClientOrder clientOrder);

    void addNewOrderHistory(OrderHistory orderHistory);
}
