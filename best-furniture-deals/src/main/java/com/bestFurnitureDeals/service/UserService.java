package com.bestFurnitureDeals.service;

import com.bestFurnitureDeals.model.User;

import java.util.List;

public interface UserService {
    User getUserById(long id);

    List<User> getAllUsers();

    User getUserByUsername(String username);

    User getUserByMail(String mail);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
