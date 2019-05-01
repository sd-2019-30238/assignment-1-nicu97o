package com.bestfurnituredeals.assignment3.dao;

import com.bestfurnituredeals.assignment3.model.database.Deal;
import com.bestfurnituredeals.assignment3.model.database.DealType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface DealDAO extends JpaRepository<Deal, Long> {
    List<Deal> findDealsByAvailableTrue();

    List<Deal> findDealsByPriceIsBetweenAndAvailableTrue(BigDecimal minPrice, BigDecimal maxPrice);

    List<Deal> findDealsByAvailableTrueAndNameContains(String name);

    List<Deal> findDealsByAvailableTrueAndDealType(DealType dealType);
}
