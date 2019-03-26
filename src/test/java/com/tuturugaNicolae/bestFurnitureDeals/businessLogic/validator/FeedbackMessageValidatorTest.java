package com.tuturugaNicolae.bestFurnitureDeals.businessLogic.validator;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.FeedbackMessageDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.Validator;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.impl.FeedbackMessageValidator;
import com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.FeedbackMessage;
import org.junit.Before;
import org.junit.Test;

import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FeedbackMessageValidatorTest {
    private Validator<FeedbackMessageDTO> validator;

    @Before
    public void setUp() {
        validator = new FeedbackMessageValidator();
    }

    @Test
    public void testValidate_shouldReturnTrue() {
        assertTrue(validator.validate(PREINSERTED_FEEDBACK_MESSAGEDTO_1));
    }

    @Test
    public void testValidateWhenTitleIsInvalid_shouldReturnFalse() {
        FeedbackMessageDTO feedbackMessage = new FeedbackMessageDTO(0l, null, "test", PREINSERTED_ORDER_HISTORYDTO_1);
        assertFalse(validator.validate(feedbackMessage));
    }

    @Test
    public void testValidateWhenDescriptionIsInvalid_shouldReturnFalse() {
        FeedbackMessageDTO feedbackMessage = new FeedbackMessageDTO(0l, "test", null, PREINSERTED_ORDER_HISTORYDTO_1);
        assertFalse(validator.validate(feedbackMessage));
    }
}
