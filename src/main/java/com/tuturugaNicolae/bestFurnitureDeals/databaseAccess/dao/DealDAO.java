package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Deal;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.DealType;

import java.math.BigDecimal;
import java.util.List;

public interface DealDAO extends GenericDAO<Deal> {
    List<Deal> findDealsByPrice(BigDecimal lowerMargin, BigDecimal upperMargin);

    List<Deal> findDealsByName(String dealsName);

    List<Deal> findDealsByType(DealType dealType);
}
