package com.bestfurnituredeals.assignment3.service.query;

import com.bestfurnituredeals.assignment3.model.database.User;

import java.util.List;

public interface UserQueryService {
    User getUserById(long id);

    List<User> getAllUsers();

    User getUserByUsername(String username);

    User getUserByMail(String mail);
}
