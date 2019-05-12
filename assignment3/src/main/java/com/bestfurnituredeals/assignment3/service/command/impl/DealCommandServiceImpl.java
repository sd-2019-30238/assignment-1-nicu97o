package com.bestfurnituredeals.assignment3.service.command.impl;

import com.bestfurnituredeals.assignment3.dao.DealDAO;
import com.bestfurnituredeals.assignment3.model.database.Deal;
import com.bestfurnituredeals.assignment3.model.database.Furniture;
import com.bestfurnituredeals.assignment3.service.command.DealCommandService;
import com.bestfurnituredeals.assignment3.service.query.DealQueryService;
import com.bestfurnituredeals.assignment3.service.query.FurnitureQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DealCommandServiceImpl implements DealCommandService {
    private DealDAO dealDAO;
    private FurnitureQueryService furnitureQueryService;
    private DealQueryService dealQueryService;

    @Autowired
    public DealCommandServiceImpl(DealDAO dealDAO, FurnitureQueryService furnitureQueryService, DealQueryService dealQueryService) {
        this.dealDAO = dealDAO;
        this.furnitureQueryService = furnitureQueryService;
        this.dealQueryService = dealQueryService;
    }

    @Override
    @PreAuthorize("hasAuthority('STAFF')")
    public void addDeal(Deal deal, long furnitureId) {
        Furniture furniture = furnitureQueryService.getFurnitureById(furnitureId);
        deal.setFurniture(furniture);
        dealDAO.save(deal);
    }

    @Override
    @PreAuthorize("hasAuthority('STAFF')")
    public void deleteDeal(long id) {
        Deal deal = dealQueryService.getDealById(id);
        dealDAO.delete(deal);
    }

    @Override
    public void updateDeal(Deal deal) {
        dealDAO.save(deal);
    }
}
