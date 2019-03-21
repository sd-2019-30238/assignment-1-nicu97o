package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.Validator;
import com.tuturugaNicolae.bestFurnitureDeals.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserValidator implements Validator<User> {
    private static final String PASSWORD_REGEX = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

    @Override
    public boolean validate(User user) {
        return validateUsername(user.getUsername()) && validateMail(user.getMail()) && validatePassword(user.getPassword());
    }

    private boolean validateUsername(String username) {
        return username != null;
    }

    private boolean validateMail(String mail) {
        return mail != null && mail.matches(PASSWORD_REGEX);
    }

    private boolean validatePassword(String password) {
        return password != null && password.length() >= 5;
    }
}
