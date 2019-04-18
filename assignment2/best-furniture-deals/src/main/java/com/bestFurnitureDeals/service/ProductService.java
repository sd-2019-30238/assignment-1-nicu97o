package com.bestFurnitureDeals.service;

import com.bestFurnitureDeals.model.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(long id);

    void addProductToOrder(long dealId, String username, int quantity);

    void deleteProduct(long id);

    List<Product> getProductsForAClientOrder(long id);

    List<Product> getProductsForAClientsCurrentOrder(String username);
}
