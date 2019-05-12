package com.bestfurnituredeals.assignment3.facade.query;

import com.bestfurnituredeals.assignment3.model.read.dto.UserDTO;

import java.util.List;

public interface UserQueryServiceFacade {
    UserDTO getUserById(long id);

    List<UserDTO> getAllUsers();

    UserDTO getUserByUsername(String username);

    UserDTO getUserByMail(String mail);
}
