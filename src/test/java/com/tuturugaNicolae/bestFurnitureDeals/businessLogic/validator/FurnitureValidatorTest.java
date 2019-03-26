package com.tuturugaNicolae.bestFurnitureDeals.businessLogic.validator;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.FurnitureDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.Validator;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.impl.FurnitureValidator;
import org.junit.Before;
import org.junit.Test;

import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.PREINSERTED_FURNITUREDTO_1;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FurnitureValidatorTest {
    private Validator<FurnitureDTO> furnitureDTOValidator;

    @Before
    public void setUp() {
        furnitureDTOValidator = new FurnitureValidator();
    }

    @Test
    public void testValidate_shouldBeSuccessful() {
        assertTrue(furnitureDTOValidator.validate(PREINSERTED_FURNITUREDTO_1));
    }

    @Test
    public void testValidateWhenNameIsNotValid_shouldFail() {
        FurnitureDTO furniture = new FurnitureDTO(PREINSERTED_FURNITUREDTO_1.getId(), null, PREINSERTED_FURNITUREDTO_1.getDescription());
        assertFalse(furnitureDTOValidator.validate(furniture));
    }

    @Test
    public void testValidateWhenDescriptionIsNotValid_shouldFail() {
        FurnitureDTO furniture = new FurnitureDTO(PREINSERTED_FURNITUREDTO_1.getId(), PREINSERTED_FURNITUREDTO_1.getName(), null);
        assertFalse(furnitureDTOValidator.validate(furniture));
    }
}
