package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.DealDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.FurnitureDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DealValidator implements Validator<DealDTO> {
    private Validator<FurnitureDTO> furnitureValidator;

    @Autowired
    public DealValidator(Validator<FurnitureDTO> furnitureValidator) {
        this.furnitureValidator = furnitureValidator;
    }

    @Override
    public boolean validate(DealDTO dealDTO) {
        return dealDTO != null && validateName(dealDTO.getName()) && validatePrice(dealDTO.getPrice())
                && validateQuantity(dealDTO.getAvailableQuantity()) && furnitureValidator.validate(dealDTO.getFurnitureDTO());
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
