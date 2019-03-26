package com.tuturugaNicolae.bestFurnitureDeals.businessLogic.validator;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.UserDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.UserTypeDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.Validator;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.impl.UserValidator;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.User;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.UserType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserValidatorTest {
    private Validator userValidator;
    private static final UserDTO VALID_USER = new UserDTO(null, "tuturuga", "123456", "tuturuganicu@gmail.com", UserTypeDTO.CLIENT);

    @Before
    public void setUp() {
        userValidator = new UserValidator();
    }

    @Test
    public void testValidateWhenUserIsValid_shouldReturnTrue() {
        assertTrue(userValidator.validate(VALID_USER));
    }

    @Test
    public void testValidateWhenUserHasInvalidUsername_shouldReturnFalse() {
        UserDTO invalidUser = new UserDTO(null, null, VALID_USER.getPassword(), VALID_USER.getMail(), VALID_USER.getUserTypeDTO());
        assertFalse(userValidator.validate(invalidUser));
    }

    @Test
    public void testValidateWhenUserHasInvalidMail_shouldReturnFalse() {
        UserDTO invalidUser = new UserDTO(null, VALID_USER.getUsername(), VALID_USER.getPassword(), "abc", VALID_USER.getUserTypeDTO());
        assertFalse(userValidator.validate(invalidUser));
    }

    @Test
    public void testValidateWhenUserHasInvalidPassword_shouldReturnFalse() {
        UserDTO invalidUser = new UserDTO(null, VALID_USER.getUsername(), "abc", VALID_USER.getMail(), VALID_USER.getUserTypeDTO());
        assertFalse(userValidator.validate(invalidUser));
    }
}
