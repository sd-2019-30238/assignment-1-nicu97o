package com.bestFurnitureDeals.service.impl;

import com.bestFurnitureDeals.model.ClientOrder;
import com.bestFurnitureDeals.model.Deal;
import com.bestFurnitureDeals.model.Product;
import com.bestFurnitureDeals.service.ProductCreatorService;

import java.math.BigDecimal;

public class NormalProductCreatorService implements ProductCreatorService {
    @Override
    public Product createProduct(ClientOrder clientOrder, Deal deal, int quantity) {
        deal.setAvailableQuantity(deal.getAvailableQuantity() - quantity);
        return new Product(quantity, deal.getPrice().multiply(BigDecimal.valueOf(quantity)), deal, clientOrder);
    }
}
