package com.tuturugaNicolae.bestFurnitureDeals.businessLogic.validator;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.DealDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.DealTypeDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.FurnitureDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.Validator;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.impl.DealValidator;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.impl.FurnitureValidator;
import com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.PREINSERTED_FURNITUREDTO_1;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DealValidatorTest {
    private Validator<DealDTO> validator;
    private Validator<FurnitureDTO> furnitureDTOValidator;

    @Before
    public void setUp() {
        furnitureDTOValidator = new FurnitureValidator();
        validator = new DealValidator(furnitureDTOValidator);
    }

    @Test
    public void testValidate_shouldBeSuccessful() {
        assertTrue(validator.validate(PreinsertedDataContainer.PREINSERTED_DEALDTO_1));
    }

    @Test
    public void testValidateWhenQuantityInvalid_shouldReturnFalse() {
        DealDTO dealDTO = new DealDTO(null, "test", DealTypeDTO.NORMAL, PREINSERTED_FURNITUREDTO_1, BigDecimal.ONE, true, -1);
        assertFalse(validator.validate(dealDTO));
    }

    @Test
    public void testValidateWhenNameInvalid_shouldReturnFalse() {
        DealDTO dealDTO = new DealDTO(null, null, DealTypeDTO.NORMAL, PREINSERTED_FURNITUREDTO_1, BigDecimal.ONE, true, 1);
        assertFalse(validator.validate(dealDTO));
    }

    @Test
    public void testValidateWhenPriceInvalid_shouldReturnFalse() {
        DealDTO dealDTO = new DealDTO(null, "test", DealTypeDTO.NORMAL, PREINSERTED_FURNITUREDTO_1, null, true, 1);
        assertFalse(validator.validate(dealDTO));
    }
}
