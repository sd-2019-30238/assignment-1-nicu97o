package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.impl;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.OrderHistoryDAO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.OrderHistory;
import org.springframework.stereotype.Repository;

@Repository
public class OrderHistoryDAOImpl extends GenericDAOImpl<OrderHistory> implements OrderHistoryDAO {
    public OrderHistoryDAOImpl() {
        super(OrderHistory.class);
    }
}
