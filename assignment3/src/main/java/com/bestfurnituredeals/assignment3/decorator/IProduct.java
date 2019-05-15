package com.bestfurnituredeals.assignment3.decorator;


import java.math.BigDecimal;

public interface IProduct {

    void decorate();

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    IProduct getProduct();
}
