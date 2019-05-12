package com.bestfurnituredeals.assignment3.factory;

import com.bestfurnituredeals.assignment3.model.database.ClientOrder;
import com.bestfurnituredeals.assignment3.model.database.Deal;
import com.bestfurnituredeals.assignment3.model.database.Product;

import java.math.BigDecimal;

public class NormalProductCreatorService implements ProductCreatorService {
    @Override
    public Product createProduct(ClientOrder clientOrder, Deal deal, int quantity) {
        deal.setAvailableQuantity(deal.getAvailableQuantity() - quantity);
        return new Product(quantity, deal.getPrice().multiply(BigDecimal.valueOf(quantity)), deal, clientOrder);
    }
}
