package com.bestfurnituredeals.assignment3.facade.command;

public interface ProductCommandServiceFacade {
    void addProductToOrder(long dealId, String username, int quantity);

    void deleteProduct(long id);
}
