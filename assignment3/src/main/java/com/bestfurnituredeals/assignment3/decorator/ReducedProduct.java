package com.bestfurnituredeals.assignment3.decorator;

import java.math.BigDecimal;

public class ReducedProduct extends ProductDecorator {
    private static final double REDUCED_PERCENTAGE = 0.95;

    public ReducedProduct(IProduct product) {
        super(product);
    }

    @Override
    public IProduct getProduct() {
        IProduct iProduct =  super.getProduct();
        iProduct.setPrice(super.getPrice().multiply(BigDecimal.valueOf(REDUCED_PERCENTAGE)));
        return iProduct;
    }
}
