package com.tuturugaNicolae.bestFurnitureDeals.validator;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.Validator;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.impl.UserValidator;
import com.tuturugaNicolae.bestFurnitureDeals.model.User;
import com.tuturugaNicolae.bestFurnitureDeals.model.UserType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserValidatorTest {
    private Validator userValidator;
    private final static User validUser = new User("tuturuga", "123456", "tuturuganicu@gmail.com", UserType.CLIENT);

    @Before
    public void setUp() {
        userValidator = new UserValidator();
    }

    @Test
    public void testValidateWhenUserIsValid_shouldReturnTrue() {
        assertTrue(userValidator.validate(validUser));
    }

    @Test
    public void testValidateWhenUserHasInvalidUsername_shouldReturnFalse() {
        User invalidUser = new User(null, validUser.getPassword(), validUser.getMail(), validUser.getUserType());
        assertFalse(userValidator.validate(invalidUser));
    }

    @Test
    public void testValidateWhenUserHasInvalidMail_shouldReturnFalse() {
        User invalidUser = new User(validUser.getUsername(), validUser.getPassword(), "abc", validUser.getUserType());
        assertFalse(userValidator.validate(invalidUser));
    }

    @Test
    public void testValidateWhenUserHasInvalidPassword_shouldReturnFalse() {
        User invalidUser = new User(validUser.getUsername(), "abc", validUser.getMail(), validUser.getUserType());
        assertFalse(userValidator.validate(invalidUser));
    }
}
