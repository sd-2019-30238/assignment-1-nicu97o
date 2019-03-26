package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.DealComputerService;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.BoughtFurniture;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Deal;

import java.math.BigDecimal;

public class PayOneAndGetTwoDealComputerService implements DealComputerService {
    @Override
    public BoughtFurniture computeFurnitureOrderDetails(ClientOrder clientOrder, Deal deal, int quantity) {
        int numberOfReducedProducts = quantity / 2;
        deal.setAvailableQuantity(deal.getAvailableQuantity() - quantity);
        BoughtFurniture boughtFurniture = new BoughtFurniture(0L, deal.getFurniture(), quantity, deal.getPrice().multiply(BigDecimal.valueOf(quantity - numberOfReducedProducts)), clientOrder);
        return boughtFurniture;
    }
}
