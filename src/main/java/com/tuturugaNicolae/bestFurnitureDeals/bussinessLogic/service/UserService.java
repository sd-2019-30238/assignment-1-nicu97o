package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.User;

import java.util.List;

public interface UserService {
    User getUserById(long id);

    User getUserByUsername(String username);

    List<User> getAllUsers();

    void updateUser(User user, String usernameOfLoggedUser);

    void deleteUser(String username);

    void addUser(User user, String password);
}
