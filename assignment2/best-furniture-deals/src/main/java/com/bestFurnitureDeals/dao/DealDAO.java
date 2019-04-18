package com.bestFurnitureDeals.dao;

import com.bestFurnitureDeals.model.Deal;
import com.bestFurnitureDeals.model.DealType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface DealDAO extends JpaRepository<Deal, Long> {
    List<Deal> findDealsByAvailableTrue();

    List<Deal> findDealsByPriceIsBetweenAndAvailableTrue(BigDecimal minPrice, BigDecimal maxPrice);

    List<Deal> findDealsByAvailableTrueAndNameContains(String name);

    List<Deal> findDealsByAvailableTrueAndDealType(DealType dealType);
}
