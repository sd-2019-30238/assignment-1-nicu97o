package com.bestfurnituredeals.assignment3.factory;

import com.bestfurnituredeals.assignment3.model.database.ClientOrder;
import com.bestfurnituredeals.assignment3.model.database.Deal;
import com.bestfurnituredeals.assignment3.model.database.Product;

public interface ProductCreatorService {
    Product createProduct(ClientOrder clientOrder, Deal deal, int quantity);
}
