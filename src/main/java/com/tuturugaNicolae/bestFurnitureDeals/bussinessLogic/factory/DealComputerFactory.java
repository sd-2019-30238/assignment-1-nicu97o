package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.factory;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.DealTypeDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.DealComputer;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl.AllProductsReductionDealComputer;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl.FixedNumberOfProductsReducedDealComputer;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl.NormalDealComputer;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl.PayOneAndGetTwoDealComputer;

public class DealComputerFactory {
    public static DealComputer create(DealTypeDTO dealTypeDTO) {
        if (dealTypeDTO.equals(DealTypeDTO.PAY_ONE_AND_TAKE_SECOND_FREE)) {
            return new PayOneAndGetTwoDealComputer();
        } else if (dealTypeDTO.equals(DealTypeDTO.EACH_PRODUCT_REDUCTION_5_PERCENT)) {
            return new AllProductsReductionDealComputer();
        } else if (dealTypeDTO.equals(DealTypeDTO.THREE_WITH_REDUCTION_25_PERCENT)) {
            return new FixedNumberOfProductsReducedDealComputer();
        }
        return new NormalDealComputer();
    }
}
