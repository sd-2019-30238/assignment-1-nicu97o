package com.bestfurnituredeals.assignment3.facade.query.impl;

import com.bestfurnituredeals.assignment3.facade.query.UserQueryServiceFacade;
import com.bestfurnituredeals.assignment3.model.database.User;
import com.bestfurnituredeals.assignment3.model.read.dto.UserDTO;
import com.bestfurnituredeals.assignment3.model.read.mapper.Mapper;
import com.bestfurnituredeals.assignment3.service.query.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserQueryServiceFacadeImpl implements UserQueryServiceFacade {
    private UserQueryService userQueryService;
    private Mapper<User, UserDTO> mapper;

    @Autowired
    public UserQueryServiceFacadeImpl(UserQueryService userQueryService, Mapper<User, UserDTO> mapper) {
        this.userQueryService = userQueryService;
        this.mapper = mapper;
    }

    @Override
    public UserDTO getUserById(long id) {
        return mapper.convertToDTO(userQueryService.getUserById(id));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userQueryService.getAllUsers().stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return mapper.convertToDTO(userQueryService.getUserByUsername(username));
    }

    @Override
    public UserDTO getUserByMail(String mail) {
        return mapper.convertToDTO(userQueryService.getUserByMail(mail));
    }
}
