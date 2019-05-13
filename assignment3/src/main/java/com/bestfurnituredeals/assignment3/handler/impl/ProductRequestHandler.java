package com.bestfurnituredeals.assignment3.handler.impl;

import com.bestfurnituredeals.assignment3.handler.RequestHandler;
import com.bestfurnituredeals.assignment3.model.write.ProductAddCommandDTO;
import com.bestfurnituredeals.assignment3.request.Request;
import com.bestfurnituredeals.assignment3.service.command.ProductCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestHandler implements RequestHandler {
    private ProductCommandService productCommandService;

    @Autowired
    public ProductRequestHandler(ProductCommandService productCommandService) {
        this.productCommandService = productCommandService;
    }

    @Override
    public void handleRequest(Request request) {
        if (request.getRequestName().equals("postProduct")) {
            postProduct(request);
        } else if (request.getRequestName().equals("deleteProduct")) {
            deleteProduct(request);
        }
    }

    private void deleteProduct(Request request) {
        productCommandService.deleteProduct((Long) request.getRequestObject());
    }

    private void postProduct(Request request) {
        long dealId = ((ProductAddCommandDTO) request.getRequestObject()).getDealId();
        String username = ((ProductAddCommandDTO) request.getRequestObject()).getBuyersName();
        int quantity = ((ProductAddCommandDTO) request.getRequestObject()).getQuantity();
        productCommandService.addProductToOrder(dealId, username, quantity);
    }
}
