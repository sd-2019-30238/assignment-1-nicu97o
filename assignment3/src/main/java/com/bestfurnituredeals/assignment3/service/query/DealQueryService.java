package com.bestfurnituredeals.assignment3.service.query;

import com.bestfurnituredeals.assignment3.model.database.Deal;
import com.bestfurnituredeals.assignment3.model.database.DealType;

import java.math.BigDecimal;
import java.util.List;

public interface DealQueryService {
    Deal getDealById(long id);

    List<Deal> getAllDeals();

    List<Deal> getAllAvailableDeals();

    List<Deal> getDealsByPrice(BigDecimal minPrice, BigDecimal maxPrice);

    List<Deal> getDealsByName(String name);

    List<Deal> getDealsByType(DealType dealType);
}
