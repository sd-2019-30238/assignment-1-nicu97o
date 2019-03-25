package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Deal;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.DealType;

import java.math.BigDecimal;
import java.util.List;

public interface DealService {
    List<Deal> getDealsByType(DealType dealType);

    List<Deal> getDealsByPrice(BigDecimal minPrice, BigDecimal maxPrice);

    List<Deal> getDealsByName(String name);

    List<Deal> getAllDeals();

    Deal getDealById(long id);

    void addDeal(Deal deal);

    void updateDeal(Deal deal);

    void deleteDeal(Deal deal);
}
