package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.ClientOrderDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.Validator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ClientOrderValidator implements Validator<ClientOrderDTO> {
    @Override
    public boolean validate(ClientOrderDTO clientOrder) {
        return validatePrice(clientOrder.getTotalPrice());
    }

    private boolean validatePrice(BigDecimal price) {
        return price.compareTo(BigDecimal.ZERO) == 0 || price.compareTo(BigDecimal.ZERO) == 1;
    }
}
