package com.tuturugaNicolae.bestFurnitureDeals.businessLogic.validator;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.ClientOrderDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.PaymentMethodDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.Validator;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.impl.ClientOrderValidator;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.PREINSERTED_CLIENT_ORDERDTO_1;
import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.PREINSERTED_USERDTO_1;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ClientOrderValidatorTest {
    private Validator<ClientOrderDTO> validator;

    @Before
    public void setUp() {
        validator = new ClientOrderValidator();
    }

    @Test
    public void testValidate_shouldBeSuccessful() {
        assertTrue(validator.validate(PREINSERTED_CLIENT_ORDERDTO_1));
    }

    @Test
    public void testValidateWhenPriceIsInvalid_shouldReturnFalse() {
        assertFalse(validator.validate(new ClientOrderDTO(null, true, PaymentMethodDTO.CASH, BigDecimal.valueOf(-1),true, PREINSERTED_USERDTO_1)));
    }
}
