package com.bestFurnitureDeals.service.impl;

import com.bestFurnitureDeals.dao.UserDAO;
import com.bestFurnitureDeals.exception.NoUserFoundException;
import com.bestFurnitureDeals.model.User;
import com.bestFurnitureDeals.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
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
