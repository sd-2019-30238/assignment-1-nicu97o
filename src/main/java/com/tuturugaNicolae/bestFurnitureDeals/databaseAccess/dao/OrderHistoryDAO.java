package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.OrderHistory;

import java.util.Optional;

public interface OrderHistoryDAO extends GenericDAO<OrderHistory> {
    Optional<OrderHistory> findOrderHistoryBasedOnClientOrderId(long clientOrderId);
}
