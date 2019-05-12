package com.bestfurnituredeals.assignment3.facade.command.impl;

import com.bestfurnituredeals.assignment3.facade.command.DealCommandServiceFacade;
import com.bestfurnituredeals.assignment3.model.database.Deal;
import com.bestfurnituredeals.assignment3.model.write.DealAddCommandDTO;
import com.bestfurnituredeals.assignment3.service.command.DealCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DealCommandServiceFacadeImpl implements DealCommandServiceFacade {
    private DealCommandService dealCommandService;

    @Autowired
    public DealCommandServiceFacadeImpl(DealCommandService dealCommandService) {
        this.dealCommandService = dealCommandService;
    }

    @Override
    public void addDeal(DealAddCommandDTO dealAddCommandDTO, long furnitureId) {
        Deal deal = new Deal();
        deal.setName(dealAddCommandDTO.getName());
        deal.setDealType(dealAddCommandDTO.getDealType());
        deal.setAvailableQuantity(dealAddCommandDTO.getAvailableQuantity());
        deal.setAvailable(dealAddCommandDTO.isAvailable());
        deal.setPrice(dealAddCommandDTO.getPrice());
        dealCommandService.addDeal(deal, furnitureId);
    }

    @Override
    public void deleteDeal(long id) {
        dealCommandService.deleteDeal(id);
    }
}
