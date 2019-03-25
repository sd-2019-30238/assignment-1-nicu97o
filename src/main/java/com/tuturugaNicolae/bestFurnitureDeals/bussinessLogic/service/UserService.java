package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.UserDTO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.User;

import java.util.List;

public interface UserService {
    UserDTO getUserById(long id);

    UserDTO getUserByUsername(String username);

    List<UserDTO> getAllUsers();

    void updateUser(UserDTO userDTO, String usernameOfLoggedUser);

    void deleteUser(String username);

    void addUser(UserDTO userDTO, String password);

    User getUserEntityByUsername(String username);
}
