package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.BoughtFurnitureDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.ClientOrderDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.DealDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.DealComputer;

import java.math.BigDecimal;

public class FixedNumberOfProductsReducedDealComputer implements DealComputer {
    private static final int NUMBER_OF_REDUCED_PRODUCTS = 3;

    @Override
    public BoughtFurnitureDTO computeFurnitureOrderDetails(ClientOrderDTO clientOrderDTO, DealDTO dealDTO, int quantity) {
        if (quantity >= NUMBER_OF_REDUCED_PRODUCTS) {
            BigDecimal reducedPrice = dealDTO.getPrice().multiply(BigDecimal.valueOf(3)).divide(BigDecimal.valueOf(0.75));
            return new BoughtFurnitureDTO(0L, dealDTO.getFurnitureDTO(), quantity, reducedPrice.add(dealDTO.getPrice().multiply(BigDecimal.valueOf(quantity - NUMBER_OF_REDUCED_PRODUCTS))),
                    clientOrderDTO);
        }
        return new BoughtFurnitureDTO(0L, dealDTO.getFurnitureDTO(), quantity, dealDTO.getPrice(), clientOrderDTO);
    }
}
