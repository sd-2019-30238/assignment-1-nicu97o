package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.DealComputerService;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.BoughtFurniture;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Deal;

import java.math.BigDecimal;

public class FixedNumberOfProductsReducedDealComputerService implements DealComputerService {
    private static final int NUMBER_OF_REDUCED_PRODUCTS = 3;

    @Override
    public BoughtFurniture computeFurnitureOrderDetails(ClientOrder clientOrder, Deal deal, int quantity) {
        if (quantity >= NUMBER_OF_REDUCED_PRODUCTS) {
            BigDecimal reducedPrice = deal.getPrice().multiply(BigDecimal.valueOf(3)).divide(BigDecimal.valueOf(0.75));
            return new BoughtFurniture(0L, deal.getFurniture(), quantity, reducedPrice.add(deal.getPrice().multiply(BigDecimal.valueOf(quantity - NUMBER_OF_REDUCED_PRODUCTS))),
                    clientOrder);
        }
        return new BoughtFurniture(0L, deal.getFurniture(), quantity, deal.getPrice(), clientOrder);
    }
}
