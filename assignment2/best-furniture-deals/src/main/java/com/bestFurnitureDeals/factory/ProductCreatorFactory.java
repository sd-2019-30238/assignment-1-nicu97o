package com.bestFurnitureDeals.factory;

import com.bestFurnitureDeals.model.DealType;
import com.bestFurnitureDeals.service.ProductCreatorService;
import com.bestFurnitureDeals.service.impl.NormalProductCreatorService;
import com.bestFurnitureDeals.service.impl.ReducedProductCreatorService;

public class ProductCreatorFactory {
    public static ProductCreatorService getInstance(DealType dealType) {
        if (dealType.equals(DealType.REDUCED)) {
            return new ReducedProductCreatorService();
        }
        return new NormalProductCreatorService();
    }
}
