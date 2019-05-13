package com.bestfurnituredeals.assignment3.handler.impl;

import com.bestfurnituredeals.assignment3.handler.RequestHandler;
import com.bestfurnituredeals.assignment3.model.database.User;
import com.bestfurnituredeals.assignment3.model.write.UserRegisterCommandDTO;
import com.bestfurnituredeals.assignment3.request.Request;
import com.bestfurnituredeals.assignment3.service.command.UserCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRequestHandler implements RequestHandler {
    private UserCommandService userCommandService;

    @Autowired
    public UserRequestHandler(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @Override
    public void handleRequest(Request request) {
        if (request.getRequestName().equals("addUser")) {
            User user = new User();
            user.setUsername(((UserRegisterCommandDTO) request.getRequestObject()).getUsername());
            user.setPassword(((UserRegisterCommandDTO) request.getRequestObject()).getPassword());
            user.setMail(((UserRegisterCommandDTO) request.getRequestObject()).getMail());
            user.setUserType(((UserRegisterCommandDTO) request.getRequestObject()).getUserType());
            userCommandService.addUser(user);
        }
    }
}
