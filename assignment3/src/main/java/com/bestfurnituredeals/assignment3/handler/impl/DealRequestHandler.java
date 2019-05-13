package com.bestfurnituredeals.assignment3.handler.impl;

import com.bestfurnituredeals.assignment3.handler.RequestHandler;
import com.bestfurnituredeals.assignment3.model.database.Deal;
import com.bestfurnituredeals.assignment3.model.write.DealAddCommandDTO;
import com.bestfurnituredeals.assignment3.request.Request;
import com.bestfurnituredeals.assignment3.service.command.DealCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DealRequestHandler implements RequestHandler {
    private DealCommandService dealCommandService;

    @Autowired
    public DealRequestHandler(DealCommandService dealCommandService) {
        this.dealCommandService = dealCommandService;
    }

    @Override
    public void handleRequest(Request request) {
        if (request.getRequestName().equals("addDeal")) {
            addDeal(request);
        } else if (request.getRequestName().equals("deleteDeal")) {
            deleteDeal(request);
        }
    }

    private void addDeal(Request request) {
        Deal deal = new Deal();
        deal.setName(((DealAddCommandDTO) request.getRequestObject()).getName());
        deal.setDealType(((DealAddCommandDTO) request.getRequestObject()).getDealType());
        deal.setAvailableQuantity(((DealAddCommandDTO) request.getRequestObject()).getAvailableQuantity());
        deal.setAvailable(((DealAddCommandDTO) request.getRequestObject()).isAvailable());
        deal.setPrice(((DealAddCommandDTO) request.getRequestObject()).getPrice());
        dealCommandService.addDeal(deal, ((DealAddCommandDTO) request.getRequestObject()).getFurnitureId());
    }

    private void deleteDeal(Request request) {
        dealCommandService.deleteDeal((Long) request.getRequestObject());
    }
}
