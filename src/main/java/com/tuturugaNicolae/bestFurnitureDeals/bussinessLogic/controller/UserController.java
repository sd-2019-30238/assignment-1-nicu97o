package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.controller;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.UserDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.SecurityContext;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.UserService;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.Validator;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.User;
import com.tuturugaNicolae.bestFurnitureDeals.exception.InvalidUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserController {
    private Mapper<User, UserDTO> mapper;
    private UserService userService;
    private Validator<UserDTO> userValidator;
    private SecurityContext securityContext;

    @Autowired
    public UserController(Mapper<User, UserDTO> mapper, UserService userService, Validator<UserDTO> userValidator, SecurityContext securityContext) {
        this.mapper = mapper;
        this.userService = userService;
        this.userValidator = userValidator;
        this.securityContext = securityContext;
    }

    public UserDTO getUserById(long id) {
        return mapper.convertToDTO(userService.getUserById(id));
    }

    public UserDTO getUserByUsername(String username) {
        return mapper.convertToDTO(userService.getUserByUsername(username));
    }

    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers().stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    public void updateUser(UserDTO userDTO, String usernameOfLoggedUser) {
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
