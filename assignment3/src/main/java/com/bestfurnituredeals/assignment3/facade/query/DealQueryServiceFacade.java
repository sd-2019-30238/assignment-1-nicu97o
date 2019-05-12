package com.bestfurnituredeals.assignment3.facade.query;

import com.bestfurnituredeals.assignment3.model.database.DealType;
import com.bestfurnituredeals.assignment3.model.read.dto.DealDTO;

import java.math.BigDecimal;
import java.util.List;

public interface DealQueryServiceFacade {
    DealDTO getDealById(long id);

    List<DealDTO> getAllDeals();

    List<DealDTO> getAllAvailableDeals();

    List<DealDTO> getDealsByPrice(BigDecimal minPrice, BigDecimal maxPrice);

    List<DealDTO> getDealsByName(String name);

    List<DealDTO> getDealsByType(DealType dealType);
}
