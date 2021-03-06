package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.FurnitureDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.Validator;
import org.springframework.stereotype.Component;

@Component
public class FurnitureValidator implements Validator<FurnitureDTO> {
    @Override
    public boolean validate(FurnitureDTO furniture) {
        return furniture != null && validateDescription(furniture.getDescription()) && validateName(furniture.getName());
    }

    private boolean validateName(String name) {
        return name != null && name.length() > 3;
    }

    private boolean validateDescription(String description) {
        return description != null && description.length() > 5;
    }
}
