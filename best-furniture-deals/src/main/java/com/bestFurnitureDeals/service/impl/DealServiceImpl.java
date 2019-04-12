package com.bestFurnitureDeals.service.impl;

import com.bestFurnitureDeals.dao.DealDAO;
import com.bestFurnitureDeals.exception.NoDealFoundException;
import com.bestFurnitureDeals.model.Deal;
import com.bestFurnitureDeals.model.DealType;
import com.bestFurnitureDeals.model.Furniture;
import com.bestFurnitureDeals.service.DealService;
import com.bestFurnitureDeals.service.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DealServiceImpl implements DealService {
    private DealDAO dealDAO;
    private FurnitureService furnitureService;

    @Autowired
    public DealServiceImpl(DealDAO dealDAO, FurnitureService furnitureService) {
        this.dealDAO = dealDAO;
        this.furnitureService = furnitureService;
    }

    @Override
    public Deal getDealById(long id) {
        Optional<Deal> deal = dealDAO.findById(id);
        return deal.orElseThrow(() -> new NoDealFoundException("No deal with id " + id + " found!"));
    }

    @Override
    public void addDeal(Deal deal, long furnitureId) {
        Furniture furniture = furnitureService.getFurnitureById(furnitureId);
        deal.setFurniture(furniture);
        dealDAO.save(deal);
    }

    @Override
    public List<Deal> getAllDeals() {
        return dealDAO.findAll();
    }

    @Override
    public List<Deal> getAllAvailableDeals() {
        return dealDAO.findDealsByAvailableTrue();
    }

    @Override
    public void deleteDeal(long id) {
        Deal deal = getDealById(id);
        dealDAO.delete(deal);
    }

    @Override
    public List<Deal> getDealsByPrice(BigDecimal minPrice, BigDecimal maxPrice) {
        return dealDAO.findDealsByPriceIsBetweenAndAvailableTrue(minPrice, maxPrice);
    }

    @Override
    public List<Deal> getDealsByName(String name) {
        return dealDAO.findDealsByAvailableTrueAndNameContains(name);
    }

    @Override
    public List<Deal> getDealsByType(DealType dealType) {
        return dealDAO.findDealsByAvailableTrueAndDealType(dealType);
    }
}
