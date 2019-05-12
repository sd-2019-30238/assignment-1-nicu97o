package com.bestfurnituredeals.assignment3.factory;

import com.bestfurnituredeals.assignment3.model.database.ClientOrder;
import com.bestfurnituredeals.assignment3.model.database.Deal;
import com.bestfurnituredeals.assignment3.model.database.Product;

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
