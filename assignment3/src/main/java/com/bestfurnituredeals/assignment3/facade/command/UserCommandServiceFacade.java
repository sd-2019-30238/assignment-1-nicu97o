package com.bestfurnituredeals.assignment3.facade.command;

import com.bestfurnituredeals.assignment3.handler.RequestHandler;
import com.bestfurnituredeals.assignment3.model.write.UserRegisterCommandDTO;

public interface UserCommandServiceFacade extends RequestHandler {
    void addUser(UserRegisterCommandDTO userRegisterCommandDTO);
}
