package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.UserDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.Validator;
import org.springframework.stereotype.Component;

@Component
public class UserValidator implements Validator<UserDTO> {
    private static final String PASSWORD_REGEX = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

    @Override
    public boolean validate(UserDTO userDTO) {
        return validateUsername(userDTO.getUsername()) && validateMail(userDTO.getMail());
    }

    private boolean validateUsername(String username) {
        return username != null;
    }

    private boolean validateMail(String mail) {
        return mail != null && mail.matches(PASSWORD_REGEX);
    }
}
