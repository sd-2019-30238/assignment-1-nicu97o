package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.controller;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.DealDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.DealTypeDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.DealService;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.Validator;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Deal;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.DealType;
import com.tuturugaNicolae.bestFurnitureDeals.exception.InvalidDealException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DealController {
    private DealService dealService;
    private Mapper<Deal, DealDTO> mapper;
    private Validator<Deal> dealValidator;

    @Autowired
    public DealController(DealService dealService, Mapper<Deal, DealDTO> mapper, Validator<Deal> dealValidator) {
        this.dealService = dealService;
        this.mapper = mapper;
        this.dealValidator = dealValidator;
    }

    public List<DealDTO> getDealsByType(DealTypeDTO dealTypeDTO) {
        return dealService.getDealsByType(DealType.valueOf(dealTypeDTO.toString())).stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    public List<DealDTO> getDealsByPrice(BigDecimal minPrice, BigDecimal maxPrice) {
        return dealService.getDealsByPrice(minPrice, maxPrice).stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    public List<DealDTO> getDealsByName(String name) {
        return dealService.getDealsByName(name).stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    public List<DealDTO> getAllDeals() {
        return dealService.getAllDeals().stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    public DealDTO getDealById(long id) {
        return mapper.convertToDTO(dealService.getDealById(id));
    }

    public void addDeal(DealDTO dealDTO) {
        Deal deal = mapper.convertToEntity(dealDTO);
        if (!dealValidator.validate(deal)) {
            throw new InvalidDealException();
        }
        dealService.addDeal(deal);
    }

    public void updateDeal(DealDTO dealDTO) {
        Deal deal = mapper.convertToEntity(dealDTO);
        if (!dealValidator.validate(deal)) {
            throw new InvalidDealException();
        }
        dealService.updateDeal(deal);
    }

    public void deleteDeal(DealDTO dealDTO) {

    }
}
