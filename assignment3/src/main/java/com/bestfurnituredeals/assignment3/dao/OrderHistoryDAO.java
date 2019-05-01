package com.bestfurnituredeals.assignment3.dao;

import com.bestfurnituredeals.assignment3.model.database.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHistoryDAO extends JpaRepository<OrderHistory, Long> {
}
