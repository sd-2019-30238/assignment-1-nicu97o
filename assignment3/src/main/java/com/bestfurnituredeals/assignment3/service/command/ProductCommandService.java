package com.bestfurnituredeals.assignment3.service.command;

public interface ProductCommandService {
    void addProductToOrder(long dealId, String username, int quantity);

    void deleteProduct(long id);
}
