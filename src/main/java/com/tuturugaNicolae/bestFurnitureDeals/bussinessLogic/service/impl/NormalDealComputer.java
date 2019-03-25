package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.BoughtFurnitureDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.ClientOrderDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.DealDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.DealComputer;

import java.math.BigDecimal;

public class NormalDealComputer implements DealComputer {
    @Override
    public BoughtFurnitureDTO computeFurnitureOrderDetails(ClientOrderDTO clientOrderDTO, DealDTO dealDTO, int quantity) {
        return new BoughtFurnitureDTO(0L, dealDTO.getFurnitureDTO(), quantity, dealDTO.getPrice().multiply(BigDecimal.valueOf(quantity)), clientOrderDTO);
    }
}
