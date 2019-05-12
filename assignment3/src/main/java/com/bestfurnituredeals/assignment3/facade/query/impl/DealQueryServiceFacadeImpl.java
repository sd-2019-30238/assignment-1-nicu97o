package com.bestfurnituredeals.assignment3.facade.query.impl;

import com.bestfurnituredeals.assignment3.facade.query.DealQueryServiceFacade;
import com.bestfurnituredeals.assignment3.model.database.Deal;
import com.bestfurnituredeals.assignment3.model.database.DealType;
import com.bestfurnituredeals.assignment3.model.read.dto.DealDTO;
import com.bestfurnituredeals.assignment3.model.read.mapper.Mapper;
import com.bestfurnituredeals.assignment3.service.query.DealQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DealQueryServiceFacadeImpl implements DealQueryServiceFacade {
    private DealQueryService dealQueryService;
    private Mapper<Deal, DealDTO> mapper;

    @Autowired
    public DealQueryServiceFacadeImpl(DealQueryService dealQueryService, Mapper<Deal, DealDTO> mapper) {
        this.dealQueryService = dealQueryService;
        this.mapper = mapper;
    }

    @Override
    public DealDTO getDealById(long id) {
        return mapper.convertToDTO(dealQueryService.getDealById(id));
    }

    @Override
    public List<DealDTO> getAllDeals() {
        return dealQueryService.getAllDeals().stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<DealDTO> getAllAvailableDeals() {
        return dealQueryService.getAllAvailableDeals().stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<DealDTO> getDealsByPrice(BigDecimal minPrice, BigDecimal maxPrice) {
        return dealQueryService.getDealsByPrice(minPrice, maxPrice).stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<DealDTO> getDealsByName(String name) {
        return dealQueryService.getDealsByName(name).stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<DealDTO> getDealsByType(DealType dealType) {
        return dealQueryService.getDealsByType(dealType).stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }
}
