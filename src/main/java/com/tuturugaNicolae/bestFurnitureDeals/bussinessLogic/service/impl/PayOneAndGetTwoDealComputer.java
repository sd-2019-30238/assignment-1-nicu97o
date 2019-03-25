package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.BoughtFurnitureDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.ClientOrderDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.DealDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.DealComputer;

import java.math.BigDecimal;

public class PayOneAndGetTwoDealComputer implements DealComputer {
    @Override
    public BoughtFurnitureDTO computeFurnitureOrderDetails(ClientOrderDTO clientOrderDTO, DealDTO dealDTO, int quantity) {
        int numberOfReducedProducts = quantity / 2;
        BoughtFurnitureDTO boughtFurnitureDTO = new BoughtFurnitureDTO(0L, dealDTO.getFurnitureDTO(), quantity, dealDTO.getPrice().multiply(BigDecimal.valueOf(quantity - numberOfReducedProducts)), clientOrderDTO);
        return boughtFurnitureDTO;
    }
}
