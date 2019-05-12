package com.bestfurnituredeals.assignment3.service.command;

import com.bestfurnituredeals.assignment3.model.database.User;

public interface UserCommandService {
    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
