package com.bestFurnitureDeals.service.impl;

import com.bestFurnitureDeals.model.ClientOrder;
import com.bestFurnitureDeals.model.Deal;
import com.bestFurnitureDeals.model.Product;
import com.bestFurnitureDeals.service.ProductCreatorService;

import java.math.BigDecimal;

public class ReducedProductCreatorService implements ProductCreatorService {
    private static final double REDUCED_PRECENTAGE = 0.95;

    @Override
    public Product createProduct(ClientOrder clientOrder, Deal deal, int quantity) {
        deal.setAvailableQuantity(deal.getAvailableQuantity() - quantity);
        return new Product(quantity, (deal.getPrice().multiply(BigDecimal.valueOf(quantity)).multiply(BigDecimal.valueOf(REDUCED_PRECENTAGE))),
                deal, clientOrder);
    }
}
