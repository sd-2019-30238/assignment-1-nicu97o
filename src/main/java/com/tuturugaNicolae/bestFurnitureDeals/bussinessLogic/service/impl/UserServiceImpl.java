package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.UserService;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.Validator;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.UserDAO;
import com.tuturugaNicolae.bestFurnitureDeals.exception.InvalidUserException;
import com.tuturugaNicolae.bestFurnitureDeals.exception.NoRightsToPerformThisOperationException;
import com.tuturugaNicolae.bestFurnitureDeals.exception.NoUserFoundException;
import com.tuturugaNicolae.bestFurnitureDeals.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private Validator<User> userValidator;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, Validator<User> userValidator) {
        this.userDAO = userDAO;
        this.userValidator = userValidator;
    }

    @Override
    public User getUserById(long id) {
        User user = userDAO.selectById(id);
        if (user == null) {
            throw new NoUserFoundException("No user with id " + id + " found!");
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> user = userDAO.findByUsername(username);
        if (!user.isPresent()) {
            throw new NoUserFoundException("No user with username " + username + " found!");
        }
        return user.get();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userDAO.selectAll();
        if (users.isEmpty()) {
            throw new NoUserFoundException();
        }
        return users;
    }

    @Override
    public void updateUser(User user, String usernameOfLoggedUser) {
        if (!userValidator.validate(user)) {
            throw new InvalidUserException();
        }
        if (!user.getUsername().equals(usernameOfLoggedUser)) {
            throw new NoRightsToPerformThisOperationException();
        }
        User oldUser = getUserByUsername(usernameOfLoggedUser);
        oldUser.setPassword(user.getPassword());
        oldUser.setMail(user.getMail());
        oldUser.setUserType(user.getUserType());
        userDAO.update(oldUser);
    }

    @Override
    public void deleteUser(String username) {
        User user = getUserByUsername(username);
        userDAO.delete(user);
    }

    @Override
    public void addUser(User user) {
        if (!userValidator.validate(user)) {
            throw new InvalidUserException();
        }
        user.setId(0L);
        userDAO.insert(user);
    }
}
