package com.bestfurnituredeals.assignment3.factory;


import com.bestfurnituredeals.assignment3.model.database.DealType;

public class ProductCreatorFactory {
    public static ProductCreatorService getInstance(DealType dealType) {
        if (dealType.equals(DealType.REDUCED)) {
            return new ReducedProductCreatorService();
        }
        return new NormalProductCreatorService();
    }
}
