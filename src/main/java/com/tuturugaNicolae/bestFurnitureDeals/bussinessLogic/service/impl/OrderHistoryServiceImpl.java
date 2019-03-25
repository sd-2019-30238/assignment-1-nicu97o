package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.ClientOrderDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.OrderHistoryDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.OrderStateDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.IsStaff;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.OrderHistoryService;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.OrderHistoryDAO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.OrderHistory;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.OrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderHistoryServiceImpl implements OrderHistoryService {
    private OrderHistoryDAO orderHistoryDAO;

    @Autowired
    public OrderHistoryServiceImpl(OrderHistoryDAO orderHistoryDAO) {
        this.orderHistoryDAO = orderHistoryDAO;
    }

    @Override
    @IsStaff
    public void updateOrderHistoryState(OrderHistory orderHistory) {
        if (orderHistory.getOrderState().equals(OrderState.PIKING)) {
            orderHistory.setOrderState(OrderState.PLACED);
        } else if (orderHistory.getOrderState().equals(OrderState.PLACED)) {
            orderHistory.setOrderState(OrderState.ACCEPTED);
        } else if (orderHistory.getOrderState().equals(OrderState.ACCEPTED)) {
            orderHistory.setOrderState(OrderState.SENT);
        } else if (orderHistory.getOrderState().equals(OrderState.SENT)) {
            orderHistory.setOrderState(OrderState.DELIVERED);
        } else if (orderHistory.getOrderState().equals(OrderState.DELIVERED)) {
            orderHistory.setOrderState(OrderState.COMPLETED);
        }
        OrderHistory oldOrderHistory = orderHistoryDAO.selectById(orderHistory.getId());
        oldOrderHistory.setOrderState(OrderState.valueOf(orderHistory.getOrderState().toString()));
        orderHistoryDAO.update(orderHistory);
    }

    @Override
    public OrderHistory getOrderHistoryBasedOnClientOrder(ClientOrder clientOrder) {
        return orderHistoryDAO.findOrderHistoryBasedOnClientOrderId(clientOrder.getId()).get();
    }

    @Override
    public void addNewOrderHistory(OrderHistory orderHistory) {
        orderHistoryDAO.insert(orderHistory);
    }
}
