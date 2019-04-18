package com.bestFurnitureDeals.facade;

import com.bestFurnitureDeals.dto.model.UserDTO;

import java.util.List;

public interface UserServiceFacade {
    UserDTO getUserById(long id);

    List<UserDTO> getAllUsers();

    UserDTO getUserByUsername(String username);

    UserDTO getUserByMail(String mail);

    void addUser(UserDTO userDTO);

    void updateUser(UserDTO userDTO);

    void deleteUser(UserDTO userDTO);
}
