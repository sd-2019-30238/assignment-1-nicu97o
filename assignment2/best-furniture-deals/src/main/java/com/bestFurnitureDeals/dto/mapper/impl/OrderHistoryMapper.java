package com.bestFurnitureDeals.dto.mapper.impl;

import com.bestFurnitureDeals.dto.mapper.Mapper;
import com.bestFurnitureDeals.dto.model.OrderHistoryDTO;
import com.bestFurnitureDeals.model.OrderHistory;
import org.springframework.stereotype.Component;

@Component
public class OrderHistoryMapper implements Mapper<OrderHistory, OrderHistoryDTO> {
    @Override
    public OrderHistory convertToEntity(OrderHistoryDTO orderHistoryDTO) {
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setId(orderHistoryDTO.getId());
        orderHistory.setOrderPlaceDateTime(orderHistoryDTO.getOrderPlaceDateTime());
        orderHistory.setOrderState(orderHistoryDTO.getOrderState());
        return orderHistory;
    }

    @Override
    public OrderHistoryDTO convertToDTO(OrderHistory orderHistory) {
        OrderHistoryDTO orderHistoryDTO = new OrderHistoryDTO();
        orderHistoryDTO.setId(orderHistory.getId());
        orderHistoryDTO.setOrderPlaceDateTime(orderHistory.getOrderPlaceDateTime());
        orderHistoryDTO.setOrderState(orderHistory.getOrderState());
        return orderHistoryDTO;
    }
}
