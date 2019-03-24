package com.tuturugaNicolae.bestFurnitureDeals.businessLogic.validator;

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
    private static final User VALID_USER = new User("tuturuga", "123456", "tuturuganicu@gmail.com", UserType.CLIENT);

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
        User invalidUser = new User(null, VALID_USER.getPassword(), VALID_USER.getMail(), VALID_USER.getUserType());
        assertFalse(userValidator.validate(invalidUser));
    }

    @Test
    public void testValidateWhenUserHasInvalidMail_shouldReturnFalse() {
        User invalidUser = new User(VALID_USER.getUsername(), VALID_USER.getPassword(), "abc", VALID_USER.getUserType());
        assertFalse(userValidator.validate(invalidUser));
    }

    @Test
    public void testValidateWhenUserHasInvalidPassword_shouldReturnFalse() {
        User invalidUser = new User(VALID_USER.getUsername(), "abc", VALID_USER.getMail(), VALID_USER.getUserType());
        assertFalse(userValidator.validate(invalidUser));
    }
}
