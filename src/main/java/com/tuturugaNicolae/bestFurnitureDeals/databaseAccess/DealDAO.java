package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess;

import com.tuturugaNicolae.bestFurnitureDeals.model.Deal;

import java.math.BigDecimal;
import java.util.List;

public interface DealDAO extends GenericDAO<Deal> {
    List<Deal> findDealsByPrice(BigDecimal lowerMargin, BigDecimal upperMargin);

    List<Deal> findDealsByName(String dealsName);
}
