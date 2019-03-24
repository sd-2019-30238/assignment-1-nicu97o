package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.DealDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.DealTypeDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.DealService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DealServiceImpl implements DealService {

    @Override
    public List<DealDTO> getDealsByType(DealTypeDTO dealTypeDTO) {
        return null;
    }

    @Override
    public List<DealDTO> getDealsByPrice(BigDecimal minPrice, BigDecimal maxPrice) {
        return null;
    }

    @Override
    public List<DealDTO> getDealsByName(String name) {
        return null;
    }

    @Override
    public List<DealDTO> getAllDeals() {
        return null;
    }

    @Override
    public DealDTO getDealById(long id) {
        return null;
    }

    @Override
    public void addDeal(DealDTO dealDTO) {

    }

    @Override
    public void updateDeal(DealDTO dealDTO) {

    }

    @Override
    public void deleteDeal(DealDTO dealDTO) {

    }
}
