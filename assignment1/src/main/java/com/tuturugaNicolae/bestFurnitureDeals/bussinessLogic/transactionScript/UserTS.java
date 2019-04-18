package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.transactionScript;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.UserDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.SecurityContext;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.Validator;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.User;
import com.tuturugaNicolae.bestFurnitureDeals.exception.InvalidUserException;
import com.tuturugaNicolae.bestFurnitureDeals.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserTS {
    private Mapper<User, UserDTO> mapper;
    private UserService userService;
    private Validator<UserDTO> userValidator;
    private SecurityContext securityContext;

    @Autowired
    public UserTS(Mapper<User, UserDTO> mapper, UserService userService, Validator<UserDTO> userValidator, SecurityContext securityContext) {
        this.mapper = mapper;
        this.userService = userService;
        this.userValidator = userValidator;
        this.securityContext = securityContext;
    }

    public UserDTO getUserByUsername(String username) {
        return mapper.convertToDTO(userService.getUserByUsername(username));
    }

    public void updateUser(UserDTO userDTO, String usernameOfLoggedUser) {
        userDTO.setPassword(userDTO.getPassword() == null || userDTO.getPassword().trim().equals("") ? securityContext.getLoggedUser().get().getPassword() : userDTO.getPassword());
        userDTO.setMail(userDTO.getMail() == null || userDTO.getMail().trim().equals("") ? securityContext.getLoggedUser().get().getMail() : userDTO.getMail());
        if (!userValidator.validate(userDTO)) {
            throw new InvalidUserException();
        }
        User user = mapper.convertToEntity(userDTO);
        userService.updateUser(user, usernameOfLoggedUser);
    }

    public void deleteUser() {
        userService.deleteUser(securityContext.getLoggedUser().get().getUsername());
        securityContext.logout();
    }

    public void addUser(UserDTO userDTO, String password) {
        if (!userValidator.validate(userDTO) || password == null || password.length() < 5) {
            throw new InvalidUserException();
        }
        User user = mapper.convertToEntity(userDTO);
        userService.addUser(user, password);
    }
}
