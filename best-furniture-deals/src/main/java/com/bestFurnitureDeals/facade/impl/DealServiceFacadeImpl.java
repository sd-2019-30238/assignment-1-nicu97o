package com.bestFurnitureDeals.facade.impl;

import com.bestFurnitureDeals.dto.mapper.Mapper;
import com.bestFurnitureDeals.dto.model.DealDTO;
import com.bestFurnitureDeals.facade.DealServiceFacade;
import com.bestFurnitureDeals.model.Deal;
import com.bestFurnitureDeals.model.DealType;
import com.bestFurnitureDeals.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DealServiceFacadeImpl implements DealServiceFacade {
    private Mapper<Deal, DealDTO> mapper;
    private DealService dealService;

    @Autowired
    public DealServiceFacadeImpl(Mapper<Deal, DealDTO> mapper, DealService dealService) {
        this.mapper = mapper;
        this.dealService = dealService;
    }

    @Override
    public DealDTO getDealById(long id) {
        return mapper.convertToDTO(dealService.getDealById(id));
    }

    @Override
    public void addDeal(DealDTO dealDTO, long furnitureId) {
        dealService.addDeal(mapper.convertToEntity(dealDTO), furnitureId);
    }

    @Override
    public List<DealDTO> getAllDeals() {
        return dealService.getAllDeals().stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<DealDTO> getAllAvailableDeals() {
        return dealService.getAllAvailableDeals().stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteDeal(long id) {
        dealService.deleteDeal(id);
    }

    @Override
    public List<DealDTO> getDealsByPrice(BigDecimal minPrice, BigDecimal maxPrice) {
        return dealService.getDealsByPrice(minPrice, maxPrice).stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<DealDTO> getDealsByName(String name) {
        return dealService.getDealsByName(name).stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<DealDTO> getDealsByType(DealType dealType) {
        return dealService.getDealsByType(dealType).stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void updateDeal(DealDTO dealDTO) {
        dealService.updateDeal(mapper.convertToEntity(dealDTO));
    }
}
