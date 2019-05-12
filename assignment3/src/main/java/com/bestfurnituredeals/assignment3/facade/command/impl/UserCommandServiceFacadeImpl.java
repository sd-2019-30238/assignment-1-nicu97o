package com.bestfurnituredeals.assignment3.facade.command.impl;

import com.bestfurnituredeals.assignment3.facade.command.UserCommandServiceFacade;
import com.bestfurnituredeals.assignment3.model.database.User;
import com.bestfurnituredeals.assignment3.model.write.UserRegisterCommandDTO;
import com.bestfurnituredeals.assignment3.service.command.UserCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCommandServiceFacadeImpl implements UserCommandServiceFacade {
    private UserCommandService userCommandService;

    @Autowired
    public UserCommandServiceFacadeImpl(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @Override
    public void addUser(UserRegisterCommandDTO userRegisterCommandDTO) {
        User user = new User();
        user.setUsername(userRegisterCommandDTO.getUsername());
        user.setPassword(userRegisterCommandDTO.getPassword());
        user.setMail(userRegisterCommandDTO.getMail());
        user.setUserType(userRegisterCommandDTO.getUserType());
        userCommandService.addUser(user);
    }
}
