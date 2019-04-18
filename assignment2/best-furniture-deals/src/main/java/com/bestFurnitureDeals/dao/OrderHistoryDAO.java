package com.bestFurnitureDeals.dao;

import com.bestFurnitureDeals.model.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHistoryDAO extends JpaRepository<OrderHistory, Long> {
}
