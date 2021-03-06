package com.tuturugaNicolae.bestFurnitureDeals.service.impl;

import com.tuturugaNicolae.bestFurnitureDeals.service.DealComputerService;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.BoughtFurniture;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Deal;

import java.math.BigDecimal;

public class FixedNumberOfProductsReducedDealComputerService implements DealComputerService {
    private static final int NUMBER_OF_REDUCED_PRODUCTS = 3;

    @Override
    public BoughtFurniture computeFurnitureOrderDetails(ClientOrder clientOrder, Deal deal, int quantity) {
        deal.setAvailableQuantity(deal.getAvailableQuantity() - quantity);
        if (quantity >= NUMBER_OF_REDUCED_PRODUCTS) {
            BigDecimal reducedPrice = deal.getPrice().multiply(BigDecimal.valueOf(3)).multiply(BigDecimal.valueOf(0.75));
            return new BoughtFurniture(0L, deal.getFurniture(), quantity, reducedPrice.add(deal.getPrice().multiply(BigDecimal.valueOf(quantity - NUMBER_OF_REDUCED_PRODUCTS))),
                    clientOrder);
        }
        return new BoughtFurniture(0L, deal.getFurniture(), quantity, deal.getPrice().multiply(BigDecimal.valueOf(quantity)), clientOrder);
    }
}
