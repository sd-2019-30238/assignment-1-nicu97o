package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.DealDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.DealTypeDTO;

import java.math.BigDecimal;
import java.util.List;

public interface DealService {
    List<DealDTO> getDealsByType(DealTypeDTO dealTypeDTO);

    List<DealDTO> getDealsByPrice(BigDecimal minPrice, BigDecimal maxPrice);

    List<DealDTO> getDealsByName(String name);

    List<DealDTO> getAllDeals();

    DealDTO getDealById(long id);

    void addDeal(DealDTO dealDTO);

    void updateDeal(DealDTO dealDTO);

    void deleteDeal(DealDTO dealDTO);
}
