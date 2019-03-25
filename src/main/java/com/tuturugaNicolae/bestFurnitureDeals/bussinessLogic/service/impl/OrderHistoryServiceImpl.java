package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.ClientOrderDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.OrderHistoryDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.OrderStateDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.IsStaff;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.OrderHistoryService;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.OrderHistoryDAO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.OrderHistory;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.OrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderHistoryServiceImpl implements OrderHistoryService {
    private Mapper<OrderHistory, OrderHistoryDTO> mapper;
    private OrderHistoryDAO orderHistoryDAO;

    @Autowired
    public OrderHistoryServiceImpl(Mapper<OrderHistory, OrderHistoryDTO> mapper, OrderHistoryDAO orderHistoryDAO) {
        this.mapper = mapper;
        this.orderHistoryDAO = orderHistoryDAO;
    }

    @Override
    @IsStaff
    public void updateOrderHistory(OrderHistoryDTO orderHistoryDTO) {
        if (orderHistoryDTO.getOrderStateDTO().equals(OrderStateDTO.PIKING)) {
            orderHistoryDTO.setOrderStateDTO(OrderStateDTO.PLACED);
        } else if (orderHistoryDTO.getOrderStateDTO().equals(OrderStateDTO.PLACED)) {
            orderHistoryDTO.setOrderStateDTO(OrderStateDTO.ACCEPTED);
        } else if (orderHistoryDTO.getOrderStateDTO().equals(OrderStateDTO.ACCEPTED)) {
            orderHistoryDTO.setOrderStateDTO(OrderStateDTO.SENT);
        } else if (orderHistoryDTO.getOrderStateDTO().equals(OrderStateDTO.SENT)) {
            orderHistoryDTO.setOrderStateDTO(OrderStateDTO.DELIVERED);
        } else if (orderHistoryDTO.getOrderStateDTO().equals(OrderStateDTO.DELIVERED)) {
            orderHistoryDTO.setOrderStateDTO(OrderStateDTO.COMPLETED);
        }
        OrderHistory orderHistory = orderHistoryDAO.selectById(orderHistoryDTO.getId());
        orderHistory.setOrderState(OrderState.valueOf(orderHistoryDTO.getOrderStateDTO().toString()));
        orderHistoryDAO.update(orderHistory);
    }

    @Override
    public OrderHistoryDTO getOrderHistoryBasedOnClientOrder(ClientOrderDTO clientOrderDTO) {
        return mapper.convertToDTO(orderHistoryDAO.findOrderHistoryBasedOnClientOrderId(clientOrderDTO.getId()).get());
    }
}
