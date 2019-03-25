package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.Validator;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ClientOrderValidator implements Validator<ClientOrder> {
    @Override
    public boolean validate(ClientOrder clientOrder) {
        return validatePrice(clientOrder.getTotalPrice());
    }

    private boolean validatePrice(BigDecimal price) {
        return price.compareTo(BigDecimal.ZERO) == 0 || price.compareTo(BigDecimal.ZERO) == 1;
    }
}
