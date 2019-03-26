package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.IsStaff;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.DealService;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.DealDAO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Deal;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.DealType;
import com.tuturugaNicolae.bestFurnitureDeals.exception.NoDealFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class DealServiceImpl implements DealService {
    private DealDAO dealDAO;

    @Autowired
    public DealServiceImpl(DealDAO dealDAO) {
        this.dealDAO = dealDAO;
    }

    @Override
    public List<Deal> getDealsByType(DealType dealType) {
        List<Deal> deals = dealDAO.findDealsByType(dealType);
        if (deals.isEmpty()) {
            throw new NoDealFoundException();
        }
        return deals;
    }

    @Override
    public List<Deal> getDealsByPrice(BigDecimal minPrice, BigDecimal maxPrice) {
        List<Deal> deals = dealDAO.findDealsByPrice(minPrice, maxPrice);
        if (deals.isEmpty()) {
            throw new NoDealFoundException();
        }
        return deals;
    }

    @Override
    public List<Deal> getDealsByName(String name) {
        List<Deal> deals = dealDAO.findDealsByName(name);
        if (deals.isEmpty()) {
            throw new NoDealFoundException();
        }
        return deals;
    }

    @Override
    public List<Deal> getAllDeals() {
        List<Deal> deals = dealDAO.selectAll();
        if (deals.isEmpty()) {
            throw new NoDealFoundException();
        }
        return deals;
    }

    @Override
    public Deal getDealById(long id) {
        Deal deal = dealDAO.selectById(id);
        if (deal == null) {
            throw new NoDealFoundException("No deal with id " + id + " found!");
        }
        return deal;
    }

    @Override
    @IsStaff
    public void addDeal(Deal deal) {
        dealDAO.insert(deal);
    }

    @Override
    @IsStaff
    public void updateDeal(Deal deal) {
        Deal oldDeal = getDealById(deal.getId());
        oldDeal.setName(deal.getName());
        oldDeal.setAvailable(deal.isAvailable());
        oldDeal.setAvailableQuantity(deal.getAvailableQuantity());
        oldDeal.setPrice(deal.getPrice());
        oldDeal.setDealType(deal.getDealType());
        dealDAO.update(oldDeal);
    }

    @Override
    @IsStaff
    public void deleteDeal(Deal deal) {
        dealDAO.delete(getDealById(deal.getId()));
    }
}
