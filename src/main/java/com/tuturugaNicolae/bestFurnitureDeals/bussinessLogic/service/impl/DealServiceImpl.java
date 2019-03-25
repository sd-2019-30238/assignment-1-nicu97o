package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.DealDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.DealTypeDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.FurnitureDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.IsStaff;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.DealService;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.Validator;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.DealDAO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Deal;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.DealType;
import com.tuturugaNicolae.bestFurnitureDeals.exception.InvalidDealException;
import com.tuturugaNicolae.bestFurnitureDeals.exception.NoDealFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DealServiceImpl implements DealService {
    private DealDAO dealDAO;
    private Mapper<Deal, DealDTO> mapper;
    private Validator<DealDTO> dealValidator;

    @Autowired
    public DealServiceImpl(DealDAO dealDAO, Mapper<Deal, DealDTO> mapper, Validator<DealDTO> dealValidator) {
        this.dealDAO = dealDAO;
        this.mapper = mapper;
        this.dealValidator = dealValidator;
    }

    @Override
    public List<DealDTO> getDealsByType(DealTypeDTO dealTypeDTO) {
        List<Deal> deals = dealDAO.findDealsByType(DealType.valueOf(dealTypeDTO.toString()));
        if (deals.isEmpty()) {
            throw new NoDealFoundException();
        }
        return deals.stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<DealDTO> getDealsByPrice(BigDecimal minPrice, BigDecimal maxPrice) {
        List<Deal> deals = dealDAO.findDealsByPrice(minPrice, maxPrice);
        if (deals.isEmpty()) {
            throw new NoDealFoundException();
        }
        return deals.stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<DealDTO> getDealsByName(String name) {
        List<Deal> deals = dealDAO.findDealsByName(name);
        if (deals.isEmpty()) {
            throw new NoDealFoundException();
        }
        return deals.stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<DealDTO> getAllDeals() {
        List<Deal> deals = dealDAO.selectAll();
        if (deals.isEmpty()) {
            throw new NoDealFoundException();
        }
        return deals.stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public DealDTO getDealById(long id) {
        return mapper.convertToDTO(getDealEntityById(id));
    }

    @Override
    @IsStaff
    public void addDeal(DealDTO dealDTO) {
        if (!dealValidator.validate(dealDTO)) {
            throw new InvalidDealException();
        }
        dealDAO.insert(mapper.convertToEntity(dealDTO));
    }

    @Override
    @IsStaff
    public void updateDeal(DealDTO dealDTO) {
        if (!dealValidator.validate(dealDTO)) {
            throw new InvalidDealException();
        }
        Deal deal = getDealEntityById(dealDTO.getId());
        deal.setAvailable(dealDTO.isAvailable());
        deal.setAvailableQuantity(dealDTO.getAvailableQuantity());
        deal.setPrice(dealDTO.getPrice());
        deal.setDealType(DealType.valueOf(dealDTO.getDealTypeDTO().toString()));
        dealDAO.update(deal);
    }

    @Override
    @IsStaff
    public void deleteDeal(DealDTO dealDTO) {
        Deal deal = getDealEntityById(dealDTO.getId());
        dealDAO.delete(deal);
    }

    private Deal getDealEntityById(long id) {
        Deal deal = dealDAO.selectById(id);
        if (deal == null) {
            throw new NoDealFoundException("No deal with id " + id + " found!");
        }
        return deal;
    }
}
