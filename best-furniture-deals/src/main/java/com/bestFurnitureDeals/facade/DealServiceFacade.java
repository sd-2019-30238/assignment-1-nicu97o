package com.bestFurnitureDeals.facade;

import com.bestFurnitureDeals.dto.model.DealDTO;
import com.bestFurnitureDeals.model.DealType;

import java.math.BigDecimal;
import java.util.List;

public interface DealServiceFacade {
    DealDTO getDealById(long id);

    void addDeal(DealDTO dealDTO, long furnitureId);

    List<DealDTO> getAllDeals();

    List<DealDTO> getAllAvailableDeals();

    void deleteDeal(long id);

    List<DealDTO> getDealsByPrice(BigDecimal minPrice, BigDecimal maxPrice);

    List<DealDTO> getDealsByName(String name);

    List<DealDTO> getDealsByType(DealType dealType);
}
