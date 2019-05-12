package com.bestfurnituredeals.assignment3.service.query;

import com.bestfurnituredeals.assignment3.model.database.Product;

import java.util.List;

public interface ProductQueryService {
    Product getProductById(long id);

    List<Product> getProductsForAClientOrder(long id);

    List<Product> getProductsForAClientsCurrentOrder(String username);
}
