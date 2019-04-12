package com.bestFurnitureDeals.facade.impl;

import com.bestFurnitureDeals.dto.mapper.Mapper;
import com.bestFurnitureDeals.dto.model.UserDTO;
import com.bestFurnitureDeals.facade.UserServiceFacade;
import com.bestFurnitureDeals.model.User;
import com.bestFurnitureDeals.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserServiceFacadeImpl implements UserServiceFacade {
    private Mapper<User, UserDTO> mapper;
    private UserService userService;

    @Autowired
    public UserServiceFacadeImpl(Mapper<User, UserDTO> mapper, UserService userService) {
        this.mapper = mapper;
        this.userService = userService;
    }

    @Override
    public UserDTO getUserById(long id) {
        return mapper.convertToDTO(userService.getUserById(id));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers().stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return mapper.convertToDTO(userService.getUserByUsername(username));
    }

    @Override
    public UserDTO getUserByMail(String mail) {
        return mapper.convertToDTO(userService.getUserByMail(mail));
    }

    @Override
    public void addUser(UserDTO userDTO) {
        userService.addUser(mapper.convertToEntity(userDTO));
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        userService.updateUser(mapper.convertToEntity(userDTO));
    }

    @Override
    public void deleteUser(UserDTO userDTO) {
        userService.deleteUser(mapper.convertToEntity(userDTO));
    }
}
