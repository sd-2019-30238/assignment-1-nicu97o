package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.Validator;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Furniture;
import org.springframework.stereotype.Component;

@Component
public class FurnitureValidator implements Validator<Furniture> {
    @Override
    public boolean validate(Furniture furniture) {
        return furniture != null && validateDescription(furniture.getDescription()) && validateName(furniture.getName());
    }

    private boolean validateName(String name) {
        return name != null && name.length() > 3;
    }

    private boolean validateDescription(String description) {
        return description != null && description.length() > 5;
    }
}
