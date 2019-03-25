package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.FurnitureDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.Validator;
import org.springframework.stereotype.Component;

@Component
public class FurnitureValidator implements Validator<FurnitureDTO> {
    @Override
    public boolean validate(FurnitureDTO furnitureDTO) {
        return furnitureDTO != null && validateDescription(furnitureDTO.getDescription()) && validateName(furnitureDTO.getName());
    }

    private boolean validateName(String name) {
        return name != null && name.length() > 3;
    }

    private boolean validateDescription(String description) {
        return description != null && description.length() > 5;
    }
}
