package com.tuturugaNicolae.bestFurnitureDeals.service.impl;

import com.tuturugaNicolae.bestFurnitureDeals.service.DealComputerService;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.BoughtFurniture;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Deal;

import java.math.BigDecimal;

public class NormalDealComputerService implements DealComputerService {
    @Override
    public BoughtFurniture computeFurnitureOrderDetails(ClientOrder clientOrder, Deal deal, int quantity) {
        deal.setAvailableQuantity(deal.getAvailableQuantity() - quantity);
        return new BoughtFurniture(0L, deal.getFurniture(), quantity, deal.getPrice().multiply(BigDecimal.valueOf(quantity)), clientOrder);
    }
}
