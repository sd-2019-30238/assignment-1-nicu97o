package com.bestfurnituredeals.assignment3.facade.command.impl;

import com.bestfurnituredeals.assignment3.facade.command.ProductCommandServiceFacade;
import com.bestfurnituredeals.assignment3.service.command.ProductCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductCommandServiceFacadeImpl implements ProductCommandServiceFacade {
    private ProductCommandService productCommandService;

    @Autowired
    public ProductCommandServiceFacadeImpl(ProductCommandService productCommandService) {
        this.productCommandService = productCommandService;
    }

    @Override
    public void addProductToOrder(long dealId, String username, int quantity) {
        productCommandService.addProductToOrder(dealId, username, quantity);
    }

    @Override
    public void deleteProduct(long id) {
        productCommandService.deleteProduct(id);
    }
}
