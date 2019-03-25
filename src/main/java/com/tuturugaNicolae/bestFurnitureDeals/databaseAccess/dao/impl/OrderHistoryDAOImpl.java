package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.impl;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.OrderHistoryDAO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.OrderHistory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrderHistoryDAOImpl extends GenericDAOImpl<OrderHistory> implements OrderHistoryDAO {
    public OrderHistoryDAOImpl() {
        super(OrderHistory.class);
    }

    @Override
    public Optional<OrderHistory> findOrderHistoryBasedOnClientOrderId(long clientOrderId) {
        return sessionFactory.getCurrentSession().createQuery("from " + OrderHistory.class.getName() + " o where o.clientOrder.id = :clientOrderId")
                .setParameter("clientOrderId", clientOrderId).uniqueResultOptional();
    }
}
