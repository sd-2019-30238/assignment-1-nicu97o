package com.bestfurnituredeals.assignment3.model.read.mapper.impl;

import com.bestfurnituredeals.assignment3.model.database.OrderHistory;
import com.bestfurnituredeals.assignment3.model.read.mapper.Mapper;
import com.bestfurnituredeals.assignment3.model.read.dto.OrderHistoryDTO;
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
