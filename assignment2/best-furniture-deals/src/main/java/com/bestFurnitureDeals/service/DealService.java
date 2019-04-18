package com.bestFurnitureDeals.service;

import com.bestFurnitureDeals.model.Deal;
import com.bestFurnitureDeals.model.DealType;

import java.math.BigDecimal;
import java.util.List;

public interface DealService {
    Deal getDealById(long id);

    void addDeal(Deal deal, long furnitureId);

    List<Deal> getAllDeals();

    List<Deal> getAllAvailableDeals();

    void deleteDeal(long id);

    List<Deal> getDealsByPrice(BigDecimal minPrice, BigDecimal maxPrice);

    List<Deal> getDealsByName(String name);

    List<Deal> getDealsByType(DealType dealType);

    void updateDeal(Deal deal);
}
