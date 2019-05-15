package com.bestfurnituredeals.assignment3.decorator;

import java.math.BigDecimal;

public abstract class ProductDecorator implements IProduct {
    private IProduct product;

    public ProductDecorator(IProduct product) {
        this.product = product;
    }

    @Override
    public BigDecimal getPrice() {
        return product.getPrice();
    }

    @Override
    public void setPrice(BigDecimal price) {
        product.setPrice(price);
    }

    public IProduct getProduct() {
        return product.getProduct();
    }
}
