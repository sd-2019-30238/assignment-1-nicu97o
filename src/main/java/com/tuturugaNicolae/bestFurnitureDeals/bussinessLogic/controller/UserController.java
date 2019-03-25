package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.controller;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.UserDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.UserService;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.Validator;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.User;
import com.tuturugaNicolae.bestFurnitureDeals.exception.InvalidUserException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserController {
    private Mapper<User, UserDTO> mapper;
    private UserService userService;
    private Validator<User> userValidator;

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
        User user = mapper.convertToEntity(userDTO);
        if (!userValidator.validate(user)) {
            throw new InvalidUserException();
        }
        userService.updateUser(user, usernameOfLoggedUser);
    }

    public void deleteUser(String username) {
        userService.deleteUser(username);
    }

    public void addUser(UserDTO userDTO, String password) {
        User user = mapper.convertToEntity(userDTO);
        if (!userValidator.validate(user) || password == null || password.length() < 5) {
            throw new InvalidUserException();
        }
        userService.addUser(user, password);
    }
}
