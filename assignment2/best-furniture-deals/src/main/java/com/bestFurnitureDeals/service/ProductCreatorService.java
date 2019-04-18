package com.bestFurnitureDeals.service;

import com.bestFurnitureDeals.model.ClientOrder;
import com.bestFurnitureDeals.model.Deal;
import com.bestFurnitureDeals.model.Product;

public interface ProductCreatorService {
    Product createProduct(ClientOrder clientOrder, Deal deal, int quantity);
}
