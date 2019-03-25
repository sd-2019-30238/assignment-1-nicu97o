package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.ClientOrderDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.OrderHistoryDTO;

public interface OrderHistoryService {
    void updateOrderHistory(OrderHistoryDTO orderHistoryDTO);

    OrderHistoryDTO getOrderHistoryBasedOnClientOrder(ClientOrderDTO clientOrderDTO);
}
