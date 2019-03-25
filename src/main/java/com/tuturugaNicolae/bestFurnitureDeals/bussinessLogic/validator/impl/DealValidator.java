package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.Validator;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Deal;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Furniture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DealValidator implements Validator<Deal> {
    private Validator<Furniture> furnitureValidator;

    @Autowired
    public DealValidator(Validator<Furniture> furnitureValidator) {
        this.furnitureValidator = furnitureValidator;
    }

    @Override
    public boolean validate(Deal deal) {
        return deal != null && validateName(deal.getName()) && validatePrice(deal.getPrice())
                && validateQuantity(deal.getAvailableQuantity()) && furnitureValidator.validate(deal.getFurniture());
    }

    private boolean validateQuantity(int quantity) {
        return quantity >= 0;
    }

    private boolean validateName(String name) {
        return name != null && name.length() >= 5;
    }

    private boolean validatePrice(BigDecimal price) {
        return price.compareTo(BigDecimal.ZERO) == 0 || price.compareTo(BigDecimal.ZERO) == 1;
    }
}
