package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.impl;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.OrderHistoryDAO;
import com.tuturugaNicolae.bestFurnitureDeals.model.OrderHistory;
import org.springframework.stereotype.Repository;

@Repository
public class OrderHistoryDAOImpl extends GenericDAOImpl<OrderHistory> implements OrderHistoryDAO {
    public OrderHistoryDAOImpl() {
        super(OrderHistory.class);
    }
}
