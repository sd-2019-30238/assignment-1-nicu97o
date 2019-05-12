package com.bestfurnituredeals.assignment3.service.command.impl;

import com.bestfurnituredeals.assignment3.dao.UserDAO;
import com.bestfurnituredeals.assignment3.model.database.User;
import com.bestfurnituredeals.assignment3.service.command.UserCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserCommandServiceImpl implements UserCommandService {
    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;

    public UserCommandServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(User user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userDAO.save(user);
    }

    @Override
    public void updateUser(User user) {
        userDAO.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userDAO.delete(user);
    }
}
