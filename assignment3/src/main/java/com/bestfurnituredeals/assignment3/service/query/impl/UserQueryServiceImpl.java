package com.bestfurnituredeals.assignment3.service.query.impl;

import com.bestfurnituredeals.assignment3.dao.UserDAO;
import com.bestfurnituredeals.assignment3.exception.NoUserFoundException;
import com.bestfurnituredeals.assignment3.model.database.User;
import com.bestfurnituredeals.assignment3.service.query.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserQueryServiceImpl implements UserQueryService {
    private UserDAO userDAO;

    @Autowired
    public UserQueryServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User getUserById(long id) {
        return userDAO.findById(id).orElseThrow(() -> new NoUserFoundException("No user found for id " + id + " !"));
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userDAO.findAll();
        if (users.isEmpty()) {
            throw new NoUserFoundException();
        }
        return users;
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.findUserByUsername(username).orElseThrow(() -> new NoUserFoundException("No user found for username " + username + " !"));
    }

    @Override
    public User getUserByMail(String mail) {
        return userDAO.findUserByMail(mail).orElseThrow(() -> new NoUserFoundException("No user found for mail " + mail + " !"));
    }
}
