package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.factory;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.DealTypeDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.DealComputerService;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl.AllProductsReductionDealComputerService;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl.FixedNumberOfProductsReducedDealComputerService;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl.NormalDealComputerService;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl.PayOneAndGetTwoDealComputerService;

public class DealComputerFactory {
    public static DealComputerService create(DealTypeDTO dealTypeDTO) {
        if (dealTypeDTO.equals(DealTypeDTO.PAY_ONE_AND_TAKE_SECOND_FREE)) {
            return new PayOneAndGetTwoDealComputerService();
        } else if (dealTypeDTO.equals(DealTypeDTO.EACH_PRODUCT_REDUCTION_5_PERCENT)) {
            return new AllProductsReductionDealComputerService();
        } else if (dealTypeDTO.equals(DealTypeDTO.THREE_WITH_REDUCTION_25_PERCENT)) {
            return new FixedNumberOfProductsReducedDealComputerService();
        }
        return new NormalDealComputerService();
    }
}
