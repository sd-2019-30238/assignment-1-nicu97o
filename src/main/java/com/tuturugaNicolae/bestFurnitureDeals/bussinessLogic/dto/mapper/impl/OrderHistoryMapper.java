package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.ClientOrderDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.OrderHistoryDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.OrderHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderHistoryMapper implements Mapper<OrderHistory, OrderHistoryDTO> {
    private Mapper<ClientOrder, ClientOrderDTO> clientOrderMapper;

    @Autowired
    public OrderHistoryMapper(Mapper<ClientOrder, ClientOrderDTO> clientOrderMapper) {
        this.clientOrderMapper = clientOrderMapper;
    }

    @Override
    public OrderHistory convertToEntity(OrderHistoryDTO orderHistoryDTO) {
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setId(orderHistoryDTO.getId());
        orderHistory.setOrderPlaceDateTime(orderHistoryDTO.getOrderPlaceDateTime());
        orderHistory.setOrderState(orderHistoryDTO.getOrderState());
        orderHistory.setClientOrder(clientOrderMapper.convertToEntity(orderHistoryDTO.getClientOrderDTO()));
        return orderHistory;
    }

    @Override
    public OrderHistoryDTO convertToDTO(OrderHistory orderHistory) {
        OrderHistoryDTO orderHistoryDTO = new OrderHistoryDTO();
        orderHistoryDTO.setId(orderHistory.getId());
        orderHistoryDTO.setOrderPlaceDateTime(orderHistory.getOrderPlaceDateTime());
        orderHistoryDTO.setOrderState(orderHistory.getOrderState());
        orderHistoryDTO.setClientOrderDTO(clientOrderMapper.convertToDTO(orderHistory.getClientOrder()));
        return orderHistoryDTO;
    }
}
