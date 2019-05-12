package com.bestfurnituredeals.assignment3.service.query.impl;

import com.bestfurnituredeals.assignment3.dao.DealDAO;
import com.bestfurnituredeals.assignment3.exception.NoDealFoundException;
import com.bestfurnituredeals.assignment3.model.database.Deal;
import com.bestfurnituredeals.assignment3.model.database.DealType;
import com.bestfurnituredeals.assignment3.service.query.DealQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DealQueryServiceImpl implements DealQueryService {
    private DealDAO dealDAO;

    @Autowired
    public DealQueryServiceImpl(DealDAO dealDAO) {
        this.dealDAO = dealDAO;
    }

    @Override
    public Deal getDealById(long id) {
        Optional<Deal> deal = dealDAO.findById(id);
        return deal.orElseThrow(() -> new NoDealFoundException("No deal with id " + id + " found!"));
    }

    @Override
    @PreAuthorize("hasAuthority('STAFF')")
    public List<Deal> getAllDeals() {
        return dealDAO.findAll();
    }

    @Override
    public List<Deal> getAllAvailableDeals() {
        return dealDAO.findDealsByAvailableTrue();
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
